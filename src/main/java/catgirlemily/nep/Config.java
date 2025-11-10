package catgirlemily.nep;

import java.util.HashSet;
import java.util.Set;

public class Config {
    public boolean ChatMessageC2SPacketChatMessage = true;
    public boolean ChatMessageC2SPacketTimestamp = true;
    public boolean ChatMessageC2SPacketSignature = true;
    public boolean ChatMessageC2sPacketCancel = true;
    
    public final Set<Class<?>> avaiblePackets = new HashSet<>();
    public final Set<Class<?>> watchedPackets = new HashSet<>();

    
}
