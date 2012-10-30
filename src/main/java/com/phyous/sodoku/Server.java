package com.phyous.sodoku;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.phyous.sodoku.thrift.*;

/**
 * Created with IntelliJ IDEA.
 * User: pyoussef
 * Date: 10/18/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server implements Sodoku.Iface {
  public String ping() {
    return "thup thump";
  }

  public String solve(String input) throws BadArgumentException{
    SodokuPuzzle p = new SodokuPuzzle(input);
    System.out.println("---------------");
    System.out.println("Request to solve: " + input);
    String solution = p.solve();
    System.out.println("Complete!");
    System.out.println("---------------");
    return solution;
  }

  public static void main(String[] args) {
    try {
      Server sodoku = new Server();
      TServerSocket serverTransport = new TServerSocket(9888);
      Sodoku.Processor processor = new Sodoku.Processor(sodoku);
      Args args1 = new Args(serverTransport);
      args1.processor(processor);
      TServer server = new TThreadPoolServer(args1);
      System.out.println("Started service successfully...");
      server.serve();
    } catch (TTransportException e) {
      e.printStackTrace();
    }

  }
}
