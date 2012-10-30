package com.phyous.sodoku;

import com.phyous.sodoku.thrift.BadArgumentException;
import com.phyous.sodoku.thrift.Sodoku;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created with IntelliJ IDEA.
 * User: pyoussef
 * Date: 10/18/12
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
  public static void main(String[] args) {
    Client c = new Client();
    String input = "862300000500908040039000180108090037000207000920010604013000490080409001000005863";
    c.start(input);
  }

  private void start(String input) {
    TTransport transport;
    //Print input
    System.out.println("Attempting to solve:");
    printSodokuString(input);

    try {
      transport = new TSocket("localhost", 9888);
      TProtocol protocol = new TBinaryProtocol(transport);
      Sodoku.Client client = new Sodoku.Client(protocol);
      transport.open();
      String solved = client.solve(input);

      // Print result
      System.out.println("Solution from server:");
      printSodokuString(solved);

      transport.close();
    } catch (TTransportException e) {
      e.printStackTrace();
    } catch (TException e) {
      e.printStackTrace();
    } catch (BadArgumentException e) {
      e.printStackTrace();
    }
  }

  private void printSodokuString(String input){
    StringBuilder sb = new StringBuilder();
    sb.append(" ---+---+---\n");
    for(int i = 0; i< 81; i++){
      if(i%9 == 0)
        sb.append("|");
      sb.append(input.charAt(i));
      if((i+1) % 3 == 0)
        sb.append("|");
      if((i+1) % 9 == 0)
        sb.append("\n");
      if ((i+1) % 27 == 0)
        sb.append(" ---+---+---\n");
    }
    System.out.println(sb.toString());
  }
}
