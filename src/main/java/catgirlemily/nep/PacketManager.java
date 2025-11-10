package catgirlemily.nep;

import catgirlemily.nep.network.PacketEvent;
import catgirlemily.nep.Utils;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PacketManager {
    public static final Config CONFIG = new Config();
    public static final EventBus BUS = new EventBus();

    public static void init() {
        // Zarejestruj paczki, które chcesz obserwować
        CONFIG.avaiblePackets.add(ChatMessageC2SPacket.class);
        CONFIG.avaiblePackets.add(PlayerMoveC2SPacket.class);
        CONFIG.avaiblePackets.add(PlayerActionC2SPacket.class);

        CONFIG.watchedPackets.add(ChatMessageC2SPacket.class);


        BUS.subscribe(new PacketManager());
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send event) {
        Utils.print(event.packet.getClass().getSimpleName());


        if (CONFIG.watchedPackets.contains(event.packet.getClass())) {
            event.cancel();
        }
    }
}
