package com.infosys.hackathon.smartfert.utils;

import java.util.HashMap;
import java.util.Map;

public class smarFert {

	public static void main(String[] args) {
		calcSub(4, 45, 35, 25);
	}
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

	public static Map<RESULT,String> calcSub(int crpArea, int aN, int aP, int aK)
	{

		Map<RESULT,String> m=new HashMap<RESULT,String>();


		/* Required nutrients calculation*/

		if(aN>=150||aP>=100||aK>=100)
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


			/* calculation for the fertilizer bags cost */

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

			double reqUreaBags1=  .25*reqUreaBags;
			double reqSSPBags1=   1*reqSSPBags;
			double reqMOPBags1=  .50*reqMOPBags;

			m.put(RESULT.UREA_PER_STAGE1,String.valueOf(reqUreaBags1));
			m.put(RESULT.SSP_PER_STAGE1,String.valueOf(reqSSPBags1));
			m.put(RESULT.MOP_PER_STAGE1,String.valueOf(reqMOPBags1));


			/*  Stage 2: Seedling stage (After 25-30 days of nursery)                          */

			double reqUreaBags2=  reqUreaBags*0.25;
			double reqSSPBags2=    0*reqSSPBags;
			double reqMOPBags2= .25*reqMOPBags;

			m.put(RESULT.UREA_PER_STAGE2,String.valueOf(reqUreaBags2));
			m.put(RESULT.SSP_PER_STAGE2,String.valueOf(reqSSPBags2));
			m.put(RESULT.MOP_PER_STAGE2,String.valueOf(reqMOPBags2));

			/*Stage 3: Panicle formation (After 25-30 days of seedling)                        */

			double reqUreaBags3=  .25*reqUreaBags;
			double reqSSPBags3=    0*reqSSPBags;
			double reqMOPBags3=  .25*reqMOPBags;

			m.put(RESULT.UREA_PER_STAGE3,String.valueOf(reqUreaBags3));
			m.put(RESULT.SSP_PER_STAGE3,String.valueOf(reqSSPBags3));
			m.put(RESULT.MOP_PER_STAGE3,String.valueOf(reqMOPBags3));


			/* Stage 4: Flowering (After 25-30 days of Panicle formation)                       */

			double reqUreaBags4=  .25*reqUreaBags;
			double reqSSPBags4=    0*reqSSPBags;
			double reqMOPBags4=    0*reqMOPBags;

			m.put(RESULT.UREA_PER_STAGE4,String.valueOf(reqUreaBags4));
			m.put(RESULT.SSP_PER_STAGE4,String.valueOf(reqSSPBags4));
			m.put(RESULT.MOP_PER_STAGE4,String.valueOf(reqMOPBags4));
		}
		return (HashMap<RESULT, String>) m;

	}

}
