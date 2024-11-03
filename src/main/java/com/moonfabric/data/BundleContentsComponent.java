package com.moonfabric.data;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.moonfabric.init.Data;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.screen.slot.Slot;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class BundleContentsComponent {
    public static final BundleContentsComponent DEFAULT = new BundleContentsComponent(List.of());
    public static final Codec<BundleContentsComponent> CODEC;
    public static final PacketCodec<RegistryByteBuf, BundleContentsComponent> PACKET_CODEC;
    private static final Fraction NESTED_BUNDLE_OCCUPANCY;
    private static final int ADD_TO_NEW_SLOT = -1;
    final List<ItemStack> stacks;
    final Fraction occupancy;

    BundleContentsComponent(List<ItemStack> stacks, Fraction occupancy) {
        this.stacks = stacks;
        this.occupancy = occupancy;
    }

    public BundleContentsComponent(List<ItemStack> stacks) {
        this(stacks, calculateOccupancy(stacks));
    }

    private static Fraction calculateOccupancy(List<ItemStack> stacks) {
        Fraction fraction = Fraction.ZERO;

        ItemStack itemStack;
        for(Iterator var2 = stacks.iterator(); var2.hasNext(); fraction = fraction.add(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.getCount(), 1)))) {
            itemStack = (ItemStack)var2.next();
        }

        return fraction;
    }

    static Fraction getOccupancy(ItemStack stack) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.get(Data.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            return NESTED_BUNDLE_OCCUPANCY.add(bundleContentsComponent.getOccupancy());
        } else {
            List<BeehiveBlockEntity.BeeData> list = (List)stack.getOrDefault(DataComponentTypes.BEES, List.of());
            return !list.isEmpty() ? Fraction.ONE : Fraction.getFraction(1, 320);
        }
    }

    public ItemStack get(int index) {
        return (ItemStack)this.stacks.get(index);
    }

    public Stream<ItemStack> stream() {
        return this.stacks.stream().map(ItemStack::copy);
    }

    public Iterable<ItemStack> iterate() {
        return this.stacks;
    }

    public Iterable<ItemStack> iterateCopy() {
        return Lists.transform(this.stacks, ItemStack::copy);
    }

    public int size() {
        return this.stacks.size();
    }

    public Fraction getOccupancy() {
        return this.occupancy;
    }

    public boolean isEmpty() {
        return this.stacks.isEmpty();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof BundleContentsComponent)) {
            return false;
        } else {
            BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)o;
            return this.occupancy.equals(bundleContentsComponent.occupancy) && ItemStack.stacksEqual(this.stacks, bundleContentsComponent.stacks);
        }
    }

    public int hashCode() {
        return ItemStack.listHashCode(this.stacks);
    }

    public String toString() {
        return "BundleContents" + String.valueOf(this.stacks);
    }

    static {
        CODEC = ItemStack.CODEC.listOf().xmap(BundleContentsComponent::new, (component) -> {
            return component.stacks;
        });
        PACKET_CODEC = ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()).xmap(BundleContentsComponent::new, (component) -> {
            return component.stacks;
        });
        NESTED_BUNDLE_OCCUPANCY = Fraction.getFraction(1, 128);
    }

    public static class Builder {
        private final List<ItemStack> stacks;
        private Fraction occupancy;

        public Builder(BundleContentsComponent base) {
            this.stacks = new ArrayList(base.stacks);
            this.occupancy = base.occupancy;
        }

        public BundleContentsComponent.Builder clear() {
            this.stacks.clear();
            this.occupancy = Fraction.ZERO;
            return this;
        }

        private int addInternal(ItemStack stack) {
            if (!stack.isStackable()) {
                return -1;
            } else {
                for(int i = 0; i < this.stacks.size(); ++i) {
                    if (ItemStack.areItemsAndComponentsEqual((ItemStack)this.stacks.get(i), stack)) {
                        return i;
                    }
                }

                return -1;
            }
        }

        private int getMaxAllowed(ItemStack stack) {
            Fraction fraction = Fraction.ONE.subtract(this.occupancy);
            return Math.min(Math.max(fraction.divideBy(BundleContentsComponent.getOccupancy(stack)).intValue(), 0), 64);
        }
        public int add(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canBeNested()) {

                // 计算能添加的数量
                int i = Math.min(stack.getCount(), this.getMaxAllowed(stack));

                // 检查当前总数是否超过320
                int totalItems = this.stacks.stream().mapToInt(ItemStack::getCount).sum();
                // 限制最多添加的数量为64，基于320 - totalItems
                int availableSpace = 320 - totalItems;
                int maxAddable = Math.min(availableSpace, 64);

                // 最终可添加数量
                i = Math.min(i, maxAddable);

                if (i == 0) {
                    return 0;
                } else {
                    this.occupancy = this.occupancy.add(BundleContentsComponent.getOccupancy(stack).multiplyBy(Fraction.getFraction(i, 1)));
                    int j = this.addInternal(stack);

                    // 如果找到了已有物品
                    if (j != -1) {
                        ItemStack itemstack = this.stacks.remove(j);
                        // 检查添加后的数量是否超过64
                        int newCount = itemstack.getCount() + i;
                        // 如果超过64就限制为64
                        if (newCount > 64) {
                            newCount = 64;
                            i = 64 - itemstack.getCount(); // 更新可添加的数量
                        }

                        ItemStack itemstack1 = itemstack.copyWithCount(newCount);
                        stack.split(i);
                        this.stacks.addFirst(itemstack1);
                    } else {
                        // 如果没有找到，直接添加新的物品
                        this.stacks.addFirst(stack.split(i));
                    }

                    return i;
                }
            } else {
                return 0;
            }
        }

        public int add(Slot slot, PlayerEntity player) {
            ItemStack itemStack = slot.getStack();
            int i = this.getMaxAllowed(itemStack);
            return this.add(slot.takeStackRange(itemStack.getCount(), i, player));
        }

        @Nullable
        public ItemStack removeFirst() {
            if (this.stacks.isEmpty()) {
                return null;
            } else {
                ItemStack itemStack = ((ItemStack)this.stacks.remove(0)).copy();
                this.occupancy = this.occupancy.subtract(BundleContentsComponent.getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.getCount(), 1)));
                return itemStack;
            }
        }

        public Fraction getOccupancy() {
            return this.occupancy;
        }

        public BundleContentsComponent build() {
            return new BundleContentsComponent(List.copyOf(this.stacks), this.occupancy);
        }
    }
}
