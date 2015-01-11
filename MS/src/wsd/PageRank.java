package wsd;

import java.util.ArrayList;

public class PageRank {
	private ArrayList<Vertice> connectedVertices;
	private Vertice vertex;
	private double d; //damping factor
	
	public PageRank(Vertice vertexA,double d){
		this.vertex=vertexA;
		this.d=d;
		setConnectedVertices(vertexA);
		calculatePageRank(vertexA);
		
		
		
	}
	
	
	public void setConnectedVertices(Vertice vertexA) {
		for(Edge e:vertexA.getEdges()){
			if(e.getValue()!=0){
				if(e.getVerticeOne()!=vertexA){
					connectedVertices.add(e.getVerticeOne());}
				}else{
					connectedVertices.add(e.getVerticeTwo());
				}
			}
		
		
	}


	/*
	 * calculates PageRank for VertexA (first round). 
	 * To get an actual the calculatePageRank method must be used afterwards. 
	 */
	public double PageRankRoundOne(Vertice vertexA, Vertice vertexB){
		double sum=0;
		for(Edge edgeAToB:vertexA.getEdges()){
			if(edgeAToB.getValue()!=0){
				//rausfinden was vertexA ist
				if(edgeAToB.getVerticeOne()==vertexA){
					sum+=divideWeights(edgeAToB.getVerticeOne(), edgeAToB.getVerticeTwo())*(1/vertexB.getEdges().size());
					
				}else{
					sum+=divideWeights(edgeAToB.getVerticeTwo(), edgeAToB.getVerticeOne())*(1/vertexB.getEdges().size());
				}				
			}
		}
		double pr=(1-d)+d*sum;
		vertexA.setPageRank(pr);
		return  pr;
	}
	
	/*
	 * the weight from VertexA and VertexB (edge value) is devided by the sum of all weights which connect to  VetexB
	 */
	public double divideWeights(Vertice vertexA, Vertice vertexB){
		double weightAB=0;
		for(Edge e:vertexA.getEdges()){
			if(e.getVerticeOne()==vertexB || e.getVerticeTwo()==vertexB){
				weightAB=e.getValue();
			}
		}
			
		double sumWeightConnectingVertexB=0;
		for(Edge e:vertexB.getEdges()){ 
			sumWeightConnectingVertexB+=e.getValue();
		}
		
		return weightAB/sumWeightConnectingVertexB;
	}
	
	/*
	 * calculate pageRank
	 */
	public void calculatePageRank(Vertice vertexA){
		for (Vertice v:connectedVertices){
			PageRankRoundOne(vertexA, v);
		}
		double sum=0;
		for (Vertice v:connectedVertices){
			sum+=divideWeights(vertexA, v)*(v.getPageRank());
		}
		double pr=(1-d)+d*sum;
		vertexA.setPageRank(pr);
	}
}
