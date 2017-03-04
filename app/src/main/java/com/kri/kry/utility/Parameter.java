package com.kri.kry.utility;

/**
 * Created by dj on 16/2/16.
 */
public class Parameter {

    //FirstRun Text
    public static final String FirstRun = "FIRST_RUN";

    //LastProcessedSMSID Initialization
    public static final String LastProcessedSMSIDFirstValue = "0";

    //LastProcessedSMSID Value
    public static Long LastProcessedSMSID = Long.getLong("0");

    //Template XML Root Node
    public static final String XMLRootNode = "resources";

    //Broadcast Actions Flag
    public static boolean HasReadSMS = false;
    public static boolean HasProcessSMS = false;

    //Attempt Count
    public static final int AttemptCount = 20;

    //SMSTypes
    public static final String SMSTypeFinancial = "Financial";

    //FinancialTransactionType
    public static final String FinancialTransactionTypeDebit = "Debit";
    public static final String FinancialTransactionTypeCredit = "Credit";

    //Firebase Cloud Messaging Topic
    public static final String FCMTopic = "FinancialTopic";

}
