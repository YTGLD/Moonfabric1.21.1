package com.moonfabric.mixin;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.MoonFabricModClient;
import com.moonfabric.MoonPost;
import net.minecraft.client.gl.JsonEffectShaderProgram;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(JsonEffectShaderProgram.class)
public class JsonEffectShaderProgramMixin {
    @Unique
    private static final List<Identifier> registry = new ArrayList<>();
    @ModifyVariable(method = "<init>",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"),
            index = 3)
    private Identifier JsonEffectShaderProgram(Identifier identifier) {
        return identifier;
    }
}
