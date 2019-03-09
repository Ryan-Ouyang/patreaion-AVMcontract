package patreaion;

import org.aion.avm.api.ABIDecoder;
import org.aion.avm.api.BlockchainRuntime;

import org.aion.avm.api.Address;

public class HelloAvm
{
    private static long timeCreated;

    private static String _sender;
    private static String _reciever;
    private static long interval;
    private static double amount;
    private static double paymentsLeft;

    static {
        timeCreated = BlockchainRuntime.getBlockTimestamp();
    }

    public static void start(String s, String r, long timeInterval, double cost, int totalPayments) {
        _sender = s;
        _reciever = r;
        interval = timeInterval;
        amount = cost;
        paymentsLeft = totalPayments;
    }

    public static String sayHello() {
        BlockchainRuntime.println("Hello Avm");
        BlockchainRuntime.println("Sender: " + _sender);
        BlockchainRuntime.println("Reciever: " + _reciever);
        BlockchainRuntime.println("Interval: " + interval);
        BlockchainRuntime.println("Amount: " + amount);
        BlockchainRuntime.println("Payments left: " + paymentsLeft);
        return String.valueOf(timeCreated);
    }

    public static byte[] main() {
        return ABIDecoder.decodeAndRunWithClass(HelloAvm.class, BlockchainRuntime.getData());
    }
}
