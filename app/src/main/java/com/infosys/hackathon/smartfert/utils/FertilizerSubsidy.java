package com.infosys.hackathon.smartfert.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * File Created by Praveen K on 07-Mar-15.
 */
public class FertilizerSubsidy {


    public enum RESULT {
        SUBSIDY_PER_FARMER,
        UREA_PER_FARMER,
        SSP_PER_FARMER,
        MOP_PER_FARMER,
        UREA_COST_PER_FARMER,
        SSP_COST_PER_FARMER,
        MOP_COST_PER_FARMER,
        UREA_PER_FARMER1,
        SSP_PER_FARMER1,
        MOP_PER_FARMER1,
        UREA_PER_FARMER2,
        SSP_PER_FARMER2,
        MOP_PER_FARMER2,
        UREA_PER_FARMER3,
        SSP_PER_FARMER3,
        MOP_PER_FARMER3,
        UREA_PER_FARMER4,
        SSP_PER_FARMER4,
        MOP_PER_FARMER4,

    }

    public Map<RESULT,String> calculateFertilizerSubsidy(int crpArea, int aN, int aP, int aK)
    {

        Map<RESULT,String> m=new HashMap<RESULT,String>();


		/* Required nutrients calculation*/

        if(aN>150||aP>100||aK>100)
        {
            System.out.println("Good soil health. Go ahead with sowing without applying fertilizer!");
        }
        else
        {
            int rN=150-aN;
            int rP=50-aP;
            int rK=50-aK;

			/* Required fertilizer bags calculation*/

            int reqUreaBags= (int) ((rN*2.17)/50)*crpArea;
            int reqSSPBags= (int) ((rP*5.5)/50)*crpArea;
            int reqMOPBags= (int) ((rK*1.6)/50)*crpArea;

            m.put(RESULT.UREA_PER_FARMER, String.valueOf(reqUreaBags));
            m.put(RESULT.SSP_PER_FARMER, String.valueOf(reqSSPBags));
            m.put(RESULT.MOP_PER_FARMER, String.valueOf(reqMOPBags));


			/* fertilizer cost for the calculation*/

            int ureaCstPfrmr=reqUreaBags*276;
            int SSPCstPfrmr=reqSSPBags*262;
            int MOPCstPfrmr=reqMOPBags*362;

            m.put(RESULT.UREA_COST_PER_FARMER, String.valueOf(ureaCstPfrmr));
            m.put(RESULT.SSP_COST_PER_FARMER, String.valueOf(SSPCstPfrmr));
            m.put(RESULT.MOP_COST_PER_FARMER, String.valueOf(MOPCstPfrmr));

			/* Total subsidy per farmer */

            int  ttlSubPfrmr=ureaCstPfrmr+SSPCstPfrmr+MOPCstPfrmr;

            m.put(RESULT.SUBSIDY_PER_FARMER, String.valueOf(ttlSubPfrmr));


			/*Fertilizer recommendation for a farmer during the entire crop cycle */

			/* Stage 1:Basal */

            int reqUreaBags1= (int) .25*reqUreaBags;
            int reqSSPBags1= (int)   1*reqSSPBags;
            int reqMOPBags1= (int) .50*reqMOPBags;

            m.put(RESULT.UREA_PER_FARMER1,String.valueOf(reqUreaBags1));
            m.put(RESULT.SSP_PER_FARMER1,String.valueOf(reqSSPBags1));
            m.put(RESULT.MOP_PER_FARMER1,String.valueOf(reqMOPBags1));




			/*  Stage 2: Seedling stage (After 25-30 days of nursery)                          */

            int reqUreaBags2= (int) .25*reqUreaBags;
            int reqSSPBags2= (int)   0*reqSSPBags;
            int reqMOPBags2= (int) .25*reqMOPBags;

            m.put(RESULT.UREA_PER_FARMER2,String.valueOf(reqUreaBags2));
            m.put(RESULT.SSP_PER_FARMER2,String.valueOf(reqSSPBags2));
            m.put(RESULT.MOP_PER_FARMER2,String.valueOf(reqMOPBags2));

			/*Stage 3: Panicle formation (After 25-30 days of seedling)                        */

            int reqUreaBags3= (int) .25*reqUreaBags;
            int reqSSPBags3= (int)   0*reqSSPBags;
            int reqMOPBags3= (int) .25*reqMOPBags;

            m.put(RESULT.UREA_PER_FARMER3,String.valueOf(reqUreaBags3));
            m.put(RESULT.SSP_PER_FARMER3,String.valueOf(reqSSPBags3));
            m.put(RESULT.MOP_PER_FARMER3,String.valueOf(reqMOPBags3));


			/* Stage 4: Flowering (After 25-30 days of Panicle formation)                       */

            int reqUreaBags4= (int) .25*reqUreaBags;
            int reqSSPBags4= (int)   0*reqSSPBags;
            int reqMOPBags4= (int) 0*reqMOPBags;

            m.put(RESULT.UREA_PER_FARMER4,String.valueOf(reqUreaBags4));
            m.put(RESULT.SSP_PER_FARMER4,String.valueOf(reqSSPBags4));
            m.put(RESULT.MOP_PER_FARMER4,String.valueOf(reqMOPBags4));
        }
        return (HashMap<RESULT, String>) m;

    }

}
