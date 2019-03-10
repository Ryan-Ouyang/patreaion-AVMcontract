package patreaion;

import org.aion.avm.api.ABIDecoder;
import org.aion.avm.api.BlockchainRuntime;

import org.aion.avm.api.Address;

import java.math.BigInteger;
import org.aion.avm.api.Result;

public class HelloAvm
{
    private static long timeCreated;

    private static String _sender;
    private static Address _reciever;
    private static long interval;
    private static double amount;
    private static int paymentsLeft;

    static {
        timeCreated = BlockchainRuntime.getBlockTimestamp();
    }

    private static void onlyOwner() {
        BlockchainRuntime.require(BlockchainRuntime.getCaller().equals(_reciever));
    }

    public static void start(String s, long timeInterval, double cost, int totalPayments) {
        _sender = s;
        _reciever = BlockchainRuntime.getOrigin();
        interval = timeInterval;
        amount = cost;
        paymentsLeft = totalPayments;
    }

    public static String withdraw() {
        // BlockchainRuntime.println("Widthdrawing: " + withdrawal);
        BlockchainRuntime.println("Contract Balance: " + BlockchainRuntime.getBalanceOfThisContract().longValue());
        onlyOwner();
        //Result result = BlockchainRuntime.call(_reciever, BigInteger.valueOf(BlockchainRuntime.getBalanceOfThisContract().longValue()), new byte[0], BlockchainRuntime.getRemainingEnergy());

        Result result = null;
        try {
        result = BlockchainRuntime.call(_reciever, BigInteger.valueOf(BlockchainRuntime.getBalanceOfThisContract().longValue()), new byte[0], BlockchainRuntime.getRemainingEnergy());
        } catch (Throwable t) {
            BlockchainRuntime.println("TOTAL: " + t);
        }
 
        BlockchainRuntime.println("CAll complete: " + result);

        if (result.isSuccess()) {
        BlockchainRuntime.println("SUCCESS");
            return ("Transfer succeeded. Remaining amount:" + BlockchainRuntime.getBalanceOfThisContract().longValue());
        } else {
        BlockchainRuntime.println("FAIL");
            return ("Transfer failed.");
        }
    }

    public static void showInfo() {
        BlockchainRuntime.println("Remaining Balance Due: "+ BlockchainRuntime.getBalanceOfThisContract().longValue());
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
