package catgirlemily.nep.mixin;

import catgirlemily.nep.PacketManager;
import catgirlemily.nep.network.PacketEvent;
import catgirlemily.nep.Utils;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public class MixinClientCommonNetworkHandler {

    @Shadow
    protected ClientConnection connection;

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
        if (connection == null) return;

        // Tworzymy event
        PacketEvent.Send event = new PacketEvent.Send(packet, connection);
        PacketManager.BUS.post(event);

        // Anulujemy wysyłkę, jeśli event został cancelled
        if (event.isCancelled()) {
            ci.cancel();
        }

        // Debug – wypisuje nazwę pakietu
        Utils.print(packet.getClass().getSimpleName());
    }
}
