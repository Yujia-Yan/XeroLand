package org.nightcode.demo.neuralNetwork;
import java.util.Random;
import java.util.Vector;
import java.io.Serializable;
import java.util.ArrayList;
import org.nightcode.demo.SkyX.Utility.Rnd;
import org.nightcode.demo.game.Param;
/*
 * ʵ�ּ򵥷���������(ͬʱ����㷴��,Elman)
 * 
 */
public class NeuralNetwork implements Serializable {
	public static double maxStimulate=Double.MAX_VALUE;
	public static double minStimulate=Double.MIN_VALUE;
        public static double maxWeight=40;
	///////////////////////////////
	ArrayList<Neuron[]> layers;
	int connection=0;
	int in;
	int out;
	int hid;
	double[] mem;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3882065459217767896L;
	public NeuralNetwork(){
		layers = new ArrayList<Neuron[]>();
	}
	public void setNet(double[] weights,int inputn,int hidn,int hidlayn,int outputn)  
        {
            layers.clear();
		int ptr=0;
		//i=0 ���з����ĵ�һ��
		Neuron[] cLay=new Neuron[hidn];
		for(int neu=0;neu<hidn;neu++){
			Neuron tmp=new Neuron();
			double weig[]=new double[inputn+2+outputn];
			for(int w=0;w<inputn+2+outputn;w++){
				weig[w]=weights[ptr++];
			}
			tmp.setWeight(weig);
			cLay[neu]=tmp;
		}
		layers.add(cLay);
		//i>0 ��ͨ����
		for(int i=1;i<hidlayn;i++){
			cLay=new Neuron[hidn];
			for(int neu=0;neu<hidn;neu++){
				Neuron tmp=new Neuron();
				double weig[]=new double[hidn+2];
				for(int w=0;w<hidn+2;w++){
					weig[w]=weights[ptr++];
				}
				tmp.setWeight(weig);
				cLay[neu]=tmp;
			}
			layers.add(cLay);
		}
		//�����
		cLay=new Neuron[outputn];
		for(int neu=0;neu<outputn;neu++){

			
			Neuron tmp=new Neuron();
			double weig[]=new double[hidn+2];
			for(int w=0;w<hidn+2;w++){
				weig[w]=weights[ptr++];
				//System.out.println(weig[w]);
			}
			
			tmp.setWeight(weig);
			cLay[neu]=tmp;
		}
		layers.add(cLay);
		in=inputn;
		out=outputn;
		hid=hidn;
		connection=ptr;
		//System.out.println(connection);
		mem=new double[outputn];
		for(int i=0;i<outputn;i++){
			mem[i]=0;
		}
	}
	public double[] getNet()  {
           
            int ptr=0;
		double[] result=new double[connection];
		for(int i=0;i<layers.size();i++){
			Neuron[] c=layers.get(i);
			for(int j=0;j<c.length;j++){
				double[] tmp=c[j].getWeight();
				for(int k=0;k<tmp.length;k++){
                                        
					//System.out.println(ptr);
					result[ptr]=tmp[k];
					ptr++;
				}
			}
		}
                //System.out.println(ptr);
		return result;
	}
        	public void mutate(float mutationRate)  {
		
		for(int i=0;i<layers.size();i++){
                    Random r=new Random();
			Neuron[] c=layers.get(i);
			for(int j=0;j<c.length;j++){
				double[] tmp=c[j].getWeight();
				for(int k=0;k<tmp.length;k++){
                                        if(Rnd.getNextFloat(0, 1)<mutationRate)
					tmp[k]=(tmp[k]+r.nextGaussian()*4)%maxWeight;
					
					//System.out.println(tmp[k]);
				}
			}
		}
	}
        
        
	public double[] eval(double[] input){
		//�����������
		
		double[] result=new double[input.length+mem.length];
		int ptr=0;
		for(int i=0;i<input.length;i++){
			result[ptr++]=input[i];
		}
		for(int i=0;i<mem.length;i++){
			result[ptr++]=mem[i];
		}
		input=result;
		{
//			System.out.println("in");
//			for(double jj:input){
//				System.out.println(jj);
//			}
			Neuron[]  cLay=layers.get(0);
			result=new double[cLay.length];
			for(int j=0;j<cLay.length;j++){
				result[j]=cLay[j].
                                        output(input);
			}
			input=result;
//			System.out.println("out");
//			for(double jj:input){
//				System.out.println(jj);
//			}
		}
		for(int i=1;i<layers.size();i++){
			Neuron[]  cLay=layers.get(i);
			result=new double[cLay.length];

//			System.out.println("in");
//			for(double jj:input){
//				System.out.println(jj);
//			}

//			System.out.println("out");
			for(int j=0;j<cLay.length;j++){
				result[j]=cLay[j].output(input);
//				System.out.println(result[j]);
			}
			
			input=result;
			
		}
		mem=result;
		return result;
	}
	public void reset(){
//		System.out.println(mem.length);
		for(int i=0;i<layers.size();i++){
			Neuron[] c=layers.get(i);
			for(Neuron n:c){
				n.reset();
			}
		}
		
		for(int i=0;i<mem.length;i++){
			mem[i]=0;
		}
//		System.out.println(mem.length);

	}
	public  void randomlize(int inputn,int hidn,int hidlayn,int outputn){
		int ptr=0;
                 layers.clear();
                Random rnd=new Random();
		//i=0 ���з����ĵ�һ��
		Neuron[] cLay=new Neuron[hidn];
		for(int neu=0;neu<hidn;neu++){
			Neuron tmp=new Neuron();
			double weig[]=new double[inputn+2+outputn];
			for(int w=0;w<inputn+2+outputn;w++){
				weig[w]=(rnd.nextGaussian());;
                                ptr++;
			}
			tmp.setWeight(weig);
			cLay[neu]=tmp;
		}
		layers.add(cLay);
		//i>0 ��ͨ����
		for(int i=1;i<hidlayn;i++){
			cLay=new Neuron[hidn];
			for(int neu=0;neu<hidn;neu++){
				Neuron tmp=new Neuron();
				double weig[]=new double[hidn+2];
				for(int w=0;w<hidn+2;w++){
					weig[w]=(rnd.nextGaussian());;
                                        ptr++;
				}
				tmp.setWeight(weig);
				cLay[neu]=tmp;
			}
			layers.add(cLay);
		}
		//�����
		cLay=new Neuron[outputn];
		for(int neu=0;neu<outputn;neu++){

			
			Neuron tmp=new Neuron();
			double weig[]=new double[hidn+2];
			for(int w=0;w<hidn+2;w++){
				weig[w]=(rnd.nextGaussian());;
                                ptr++;
				//System.out.println(weig[w]);
			}
			
			tmp.setWeight(weig);
			cLay[neu]=tmp;
		}
		layers.add(cLay);
		in=inputn;
		out=outputn;
		hid=hidn;
		connection=ptr;
		//System.out.println(connection);
		mem=new double[outputn];
		for(int i=0;i<outputn;i++){
			mem[i]=0;
		}
                
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sigmund(-30));
		NeuralNetwork a=new NeuralNetwork();
		a.neuroTest();
		double w[]={1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,
				1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,
				1,1,1,0,1};

                a.randomlize( 2, 3, 2, 1);
                w=a.getNet();
                                for(double i:w){
                    System.out.print(i);
                }
                System.out.println();
                System.out.println(w.length);
                a.setNet( w,2, 3, 2, 1);
                                w=a.getNet();
                                for(double i:w){
                    System.out.print(i);
                }
                System.out.println();

		for(int i=0;i<3;i++)
		System.out.println(a.eval(new double[]{1d,1d})[0]);
		a.reset();
		System.out.println("--------");
		for(int i=0;i<3;i++)
		System.out.println(a.eval(new double[]{1d,1d})[0]);
		a.randomlize(4, 300, 10, 1);

		//System.out.println(a.eval(new double[]{4d,2d,-5d,-3d})[0]);
		//for(int i=0;i<30;i++)
		//System.out.println(a.eval(new double[]{0d,0d,0d,0d})[0]);
//
	}
	private void neuroTest(){
		double testW[]={1,2,3};
		double testI[]={-2,1};
		Neuron aNe=new Neuron();
		aNe.setWeight(testW);
		System.out.println(aNe.output(testI));
	}
	public class Neuron implements Serializable{
		double[] weight;
		double mem=0;
		public Neuron(){
		}
		public double[] getWeight(){
			return weight;
		}
		/*
		 * ������ԪȨֵ
		 */
		public void setWeight(double[] w){
			weight=w;
		}
		public double output(double[] input){
			double result=0;
			//�ӷ���͵�Ԫ
//			System.out.println("weight");
//			for(double jj:weight){
//				System.out.println(jj);
//			}
			
			for(int i=0;i<input.length-1;i++){
				result+=input[i]*weight[i];
				if(result>maxStimulate){
					return sigmund(maxStimulate);
					
				}
				if(result<minStimulate){
					return sigmund(minStimulate);
				}
			}
			result+=weight[input.length-1]*mem;
			if(result>maxStimulate){
				return sigmund(maxStimulate);
			}
			if(result<minStimulate){
				return sigmund(minStimulate);
			}
			//��ȥ��ֵƫ��
			result-=weight[input.length];
			mem=sigmund(result);
			//ͨ������
			return mem;
		}
		public void reset(){
			mem=0;
		}
		

	}
	
	//sigmund����:ӳ�䵽(0,1)���������
	public static  double sigmund(double value){
		return 1/(1+Math.exp(-value));
	}
}