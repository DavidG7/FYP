package finalyearproject.drawer.Constants;

/**
 * Created by Dvaid on 24/11/2014.
 */

public class Constants {
    public static final String STOCK_INDEXES = "\"AIB.IR\",\"BIR.IR\",\"DOY.IR\",\"EIL1.IR\",\"DOP.IR\",\"YZA.IR\",\"DD7D.IR\",\"DD7E.IR\",\"GCC.IR\",\"FKV.IR\"," +
            "\"DQ5.IR\",\"DD8B.IR\",\"CRG.IR\",\"DD8A.IR\",\"DHG.IR\",\"DLE.IR\",\"GUI.IR\",\"DQ7.IR\",\"DRS.IR\",\"FAC.IR\"," +
            "\"FOI.IR\",\"EG7.IR\",\"GYQ.IR\",\"FQ3.IR\",\"GAME.IR\",\"GL9.IR\",\"8GW.IR\",\"GN1.IR\",\"HBRN.IR\",\"IJG.IR\"," +
            "\"IPDC.IR\",\"IR5B.IR\",\"IRES.IR\",\"IETF.IR\",\"KJY.IR\",\"JEV.IR\",\"KRZ.IR\",\"KRX.IR\",\"MSTY.IR\",\"3MP.IR\"," +
            "\"MIO.IR\",\"OIZ.IR\",\"ORQ.IR\",\"OVXA.IR\",\"PLS.IR\",\"IL0.IR\",\"EG5A.IR\",\"P8ET.IR\",\"OKRA.IR\",\"PZQA.IR\"," +
            "\"RY4B.IR\",\"SK3.IR\",\"TCO.IR\",\"T7O.IR\",\"TQW.IR\",\"MDY.IR\",\"ZAZ.IR\"";
}



//Name of stock: QUOTE(NAME)
//Ticker Id : QUOTE(SYMBOL)
//Current Price:QUOTE(!NULL:ASK,NULL:CHANGEINPERCENT*PREVIOUSCLOSE)
//Indication whether stock has risen or fallen: QUOTE(CHANGEINPERCENT)
//Total number held: SQLDATABASE(EACH STOCK WILL HAVE A NUMBER BOUGHT ASSOCIATED WITH IT)
//Total value: SQLDATABASE (HELD*CURRENT PRICE)
//Total cost: (HELD OF EACH TRANCHE * BOUGHT PRICE, THEN ADDED TOGETHER)



