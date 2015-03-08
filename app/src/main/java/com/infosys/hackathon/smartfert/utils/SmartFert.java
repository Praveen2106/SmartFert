package com.infosys.hackathon.smartfert.utils;

import java.util.HashMap;
import java.util.Map;

public class SmartFert {

	public enum RESULT
    {
		SUBSIDY_PER_FARMER,
		UREA_PER_FARMER,
		SSP_PER_FARMER,
		MOP_PER_FARMER,
		UREA_COST_PER_FARMER,
		SSP_COST_PER_FARMER,
		MOP_COST_PER_FARMER,
		UREA_PER_STAGE1,
		SSP_PER_STAGE1,
		MOP_PER_STAGE1,
		UREA_PER_STAGE2,
		SSP_PER_STAGE2,
		MOP_PER_STAGE2,
		UREA_PER_STAGE3,
		SSP_PER_STAGE3,
		MOP_PER_STAGE3,
		UREA_PER_STAGE4,
		SSP_PER_STAGE4,
		MOP_PER_STAGE4,

	}

    public Map<RESULT,String> calcSub(int crpArea, int aN, int aP, int aK)
    {

        Map<RESULT,String> m=new HashMap<RESULT,String>();
        int ureaCstPfrmr = 0, SSPCstPfrmr = 0, MOPCstPfrmr = 0;
		/* Required nutrients calculation*/

        if(aN>=150 && aP>=100 &&aK>=100)
        {
            System.out.println("Good soil health. Go ahead with sowing without applying fertilizer!");
        }
        else
        {
            int rN=150-aN;

            if (rN > 0) {
                int reqUreaBags= (int) ((rN*2.17)/50)*crpArea;
                m.put(RESULT.UREA_PER_FARMER, String.valueOf(reqUreaBags));
                ureaCstPfrmr=reqUreaBags*276;
                m.put(RESULT.UREA_COST_PER_FARMER, String.valueOf(ureaCstPfrmr));
                /*Fertilizer recommendation for a farmer during the entire crop cycle */

                /* Stage 1:Basal */
                double reqUreaBags1=  .25*reqUreaBags;
                m.put(RESULT.UREA_PER_STAGE1,String.valueOf(reqUreaBags1));

                /*  Stage 2: Seedling stage (After 25-30 days of nursery)                          */
                double reqUreaBags2=  reqUreaBags*0.25;
                m.put(RESULT.UREA_PER_STAGE2,String.valueOf(reqUreaBags2));

                /*Stage 3: Panicle formation (After 25-30 days of seedling)                        */
                double reqUreaBags3=  .25*reqUreaBags;
                m.put(RESULT.UREA_PER_STAGE3,String.valueOf(reqUreaBags3));

                /* Stage 4: Flowering (After 25-30 days of Panicle formation)                       */
                double reqUreaBags4=  .25*reqUreaBags;
                m.put(RESULT.UREA_PER_STAGE4,String.valueOf(reqUreaBags4));
            } else {
                m.put(RESULT.UREA_PER_FARMER, String.valueOf(0));
                m.put(RESULT.UREA_COST_PER_FARMER, String.valueOf(ureaCstPfrmr));
                m.put(RESULT.UREA_PER_STAGE1,String.valueOf(0));
                m.put(RESULT.UREA_PER_STAGE2,String.valueOf(0));
                m.put(RESULT.UREA_PER_STAGE3,String.valueOf(0));
                m.put(RESULT.UREA_PER_STAGE4,String.valueOf(0));
            }
            int rP=50-aP;

            if (rP > 0) {
                int reqSSPBags= (int) ((rP*5.5)/50)*crpArea;
                m.put(RESULT.SSP_PER_FARMER, String.valueOf(reqSSPBags));
                SSPCstPfrmr=reqSSPBags*262;
                m.put(RESULT.SSP_COST_PER_FARMER, String.valueOf(SSPCstPfrmr));

                double reqSSPBags1=   1*reqSSPBags;
                m.put(RESULT.SSP_PER_STAGE1,String.valueOf(reqSSPBags1));

                double reqSSPBags2=    0*reqSSPBags;
                m.put(RESULT.SSP_PER_STAGE2,String.valueOf(reqSSPBags2));

                double reqSSPBags3=    0*reqSSPBags;
                m.put(RESULT.SSP_PER_STAGE3,String.valueOf(reqSSPBags3));

                double reqSSPBags4=    0*reqSSPBags;
                m.put(RESULT.SSP_PER_STAGE4,String.valueOf(reqSSPBags4));
            } else {
                m.put(RESULT.SSP_PER_FARMER, String.valueOf(0));
                m.put(RESULT.SSP_COST_PER_FARMER, String.valueOf(SSPCstPfrmr));
                m.put(RESULT.SSP_PER_STAGE1,String.valueOf(0));
                m.put(RESULT.SSP_PER_STAGE2,String.valueOf(0));
                m.put(RESULT.SSP_PER_STAGE3,String.valueOf(0));
                m.put(RESULT.SSP_PER_STAGE4,String.valueOf(0));
            }
            int rK=50-aK;

            if (rK > 0) {
                /* Required fertilizer bags calculation*/
                int reqMOPBags= (int) ((rK*1.6)/50)*crpArea;
                m.put(RESULT.MOP_PER_FARMER, String.valueOf(reqMOPBags));
                MOPCstPfrmr=reqMOPBags*362;
                m.put(RESULT.MOP_COST_PER_FARMER, String.valueOf(MOPCstPfrmr));

                double reqMOPBags1=  .50*reqMOPBags;
                m.put(RESULT.MOP_PER_STAGE1,String.valueOf(reqMOPBags1));

                double reqMOPBags2= .25*reqMOPBags;
                m.put(RESULT.MOP_PER_STAGE2,String.valueOf(reqMOPBags2));

                double reqMOPBags3=  .25*reqMOPBags;
                m.put(RESULT.MOP_PER_STAGE3,String.valueOf(reqMOPBags3));

                double reqMOPBags4=    0*reqMOPBags;
                m.put(RESULT.MOP_PER_STAGE4,String.valueOf(reqMOPBags4));
            } else {
                m.put(RESULT.MOP_PER_FARMER, String.valueOf(0));
                m.put(RESULT.MOP_COST_PER_FARMER, String.valueOf(MOPCstPfrmr));
                m.put(RESULT.MOP_PER_STAGE1,String.valueOf(0));
                m.put(RESULT.MOP_PER_STAGE2,String.valueOf(0));
                m.put(RESULT.MOP_PER_STAGE3,String.valueOf(0));
                m.put(RESULT.MOP_PER_STAGE4,String.valueOf(0));
            }

			/* Total subsidy per farmer */

            int  ttlSubPfrmr=ureaCstPfrmr+SSPCstPfrmr+MOPCstPfrmr;

            m.put(RESULT.SUBSIDY_PER_FARMER, String.valueOf(ttlSubPfrmr));
        }
        return (HashMap<RESULT, String>) m;
    }

}
