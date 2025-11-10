package catgirlemily.nep;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Utils {

    public static void print(String text) {
    var mc = MinecraftClient.getInstance();
    if (mc == null || mc.player == null) return;

    mc.player.sendMessage(Text.of(text), false);
}


    public static void printPacket(String title, String... lines) {
    var mc = MinecraftClient.getInstance();
    if (mc == null || mc.player == null) return;

    StringBuilder sb = new StringBuilder();
    sb.append(">> ").append(title).append(" [\n");

    for (String line : lines) {
        sb.append(line).append("\n");
    }

    sb.append("]");

    mc.player.sendMessage(Text.of(sb.toString()), false);
    }
}
