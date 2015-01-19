package wsd;

import java.util.ArrayList;

public class PageRank {
	private ArrayList<Vertice> connectedVertices=new ArrayList<Vertice>();
	private Vertice vertex;
	private double d; //damping factor
	
	public PageRank(Vertice vertexA,double d){
		this.vertex=vertexA;
		this.d=d;
		setConnectedVertices(this.vertex);
		
		calculatePageRank(this.vertex);
		
		
		
	}
	
	
	public void setConnectedVertices(Vertice vertexA) {
		for(Edge e:vertexA.getEdges()){
			if(e.getValue().intValue()>0.0){
				//System.out.println("darf nicht null sein: "+e.getValue().intValue());
				if(!e.getVerticeOne().equals(vertexA)){
					connectedVertices.add(e.getVerticeOne());}
				}else{
					connectedVertices.add(e.getVerticeTwo());
				}
			}
		
		
	}


	/**
	 * calculates PageRank for VertexA (first round). 
	 * To get an actual the calculatePageRank method must be used afterwards. 
	 */
	public void PageRankRoundOne(Vertice vertexA, Vertice vertexB){
		double sum=0;
		double tmp=divideWeights(vertexA,vertexB)*(1.0/vertexB.getEdges().size());
		//System.out.println("div: "+divideWeights(vertexA, vertexB));
		//System.out.println("1/vertebEdges: "+(1.0/vertexB.getEdges().size()));
//		for(Edge edgeAToB:vertexA.getEdges()){
//			
//			if(edgeAToB.getValue().intValue()!=0){
//				//System.out.println("edge sollte nich null sein: "+edgeAToB.getValue());
//				//rausfinden was vertexA ist
//				if(edgeAToB.getVerticeOne().equals(vertexA)){
//					//sum+=(divideWeights(edgeAToB.getVerticeOne(), edgeAToB.getVerticeTwo())*(1.0/vertexB.getEdges().size()));
////					System.out.println("divide: "+(divideWeights(edgeAToB.getVerticeOne(), edgeAToB.getVerticeTwo())));
////					System.out.println("vertex edges size: "+1.0/vertexB.getEdges().size());
////					System.out.println("tmp sum: "+sum);
//					
//				}else{
//					sum+=(divideWeights(edgeAToB.getVerticeTwo(), edgeAToB.getVerticeOne())*(1.0/vertexB.getEdges().size()));
//				}				
//			}
//		}
		//System.out.println("sum: "+sum);
		//double pr=((1.0-d)+d*sum);
		//System.out.println("tmp: "+tmp);
		
		vertexA.setTmpPageRank(vertexA.getTmpPageRank()+tmp);
		//Double test=new Double(vertexA.getPageRank());
		//System.out.println(test.isNaN());
		//System.out.println("pr vertex a: " +vertexA.getPageRank());
		//return  pr;
	}
	
	/**
	 * the weight from VertexA and VertexB (edge value) is devided by the sum of all weights which connect to  VetexB
	 */
	public double divideWeights(Vertice vertexA, Vertice vertexB){
		double weightAB=0.0;
		
		
		
		
		for(Edge e:vertexA.getEdges()){
			
			
			if(e.getVerticeOne().equals(vertexB)&&e.getVerticeTwo().equals(vertexA)){
				weightAB=e.getValue();
				//System.out.println("bitte nicht null: "+e.getValue());
			}
			
			//System.out.println("e.getvalue: "+e.getValue().doubleValue());
			//wenn edge eine der beiden vertices von endge e vertex b ist, dann soll weightAB den wert dieses edges bekommen 
//			if((e.getVerticeOne().equals(vertexB) || e.getVerticeTwo().equals(vertexB))/*&&e.getValue().intValue()!=0*/){
//				
//				weightAB=e.getValue();
//				//System.out.println("weigtAB, soll nicht null sein: "+weightAB);
//				//System.out.println("in if");
//				
//			}
		}
		System.out.println("weight: "+weightAB);
			
		double sumWeightConnectingVertexB=0.0;
		for(Edge e:vertexB.getEdges()){ 
			sumWeightConnectingVertexB+=e.getValue();
		}
		//SSystem.out.println("weight/sum: "+weightAB/sumWeightConnectingVertexB);
		
		return weightAB/sumWeightConnectingVertexB;
	}
	
	/**
	 * calculate pageRank
	 */
	public void calculatePageRank(Vertice vertexA){
		for (Vertice v:connectedVertices){
			PageRankRoundOne(vertexA, v);
			
//			System.out.println("pr round 1 vA:" +vertexA.getPageRank());
//			System.out.println("pr round 1 v:" +v.getPageRank());
		}
		vertexA.setPageRank(vertexA.getTmpPageRank());
		System.out.println("pr after round 1 vA:" +vertexA.getPageRank());
		
		double sum=0.0;
		for (Vertice v:connectedVertices){
			//System.out.println("pr1 :"+v.getPageRank());
			sum+=divideWeights(vertexA, v)*(v.getPageRank());
			//System.out.println(sum);
		}
		double pr=(1-d)+d*sum;
		vertexA.setPageRank(pr);
	}
}
