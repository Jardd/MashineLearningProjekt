package wsd;

import java.util.ArrayList;

public class PageRank_2 {
	
	Vertice main_vetex;
	ArrayList<Vertice> connectingVertices=new ArrayList();
	ArrayList<Edge> edgesFromMainVertex=new ArrayList();
	double d=0.5;
	public PageRank_2(Vertice main_vertex){
		
		this.main_vetex=main_vertex;
		setEdgesFromMainVertex();
		setConnectingVertices();
		
		
	}
	

	public ArrayList<Edge> getEdgesFromMainVertex() {
		return edgesFromMainVertex;
	}

	public void setEdgesFromMainVertex() {
		for(Edge e:this.main_vetex.getEdges()){
			if(e.getValue().intValue()!=0){
				this.edgesFromMainVertex.add(e);
			}
		}
	}

	public Vertice getMain_vetex() {
		return main_vetex;
	}

	public void setMain_vetex(Vertice main_vetex) {
		this.main_vetex = main_vetex;
	}

	public ArrayList<Vertice> getConnectingVertices() {
		return connectingVertices;
	}

	public void setConnectingVertices() {
		for(Edge e:this.edgesFromMainVertex){
			if(e.getValue().intValue()!=0){
				//System.out.println("edge value: "+e.getValue().intValue());
				for(Vertice v:e.getAllVertices()){
				if(!v.equals(this.main_vetex)){
					this.connectingVertices.add(v);
					}
				}
			}
			
		}
	}
	public double division(Vertice vertexB){
			//Sum up the weights of all edges from vertex B
			double sumAllEdges=0.0;
			ArrayList<Edge>edges=new ArrayList<Edge>(vertexB.getEdges());
			for(Edge e:edges){
				sumAllEdges+=e.getValue();
			}
			//System.out.println(sumAllEdges);
			//get the value of the edge from the mainVetex and VetexB
			double edgeMainB=0.0;
			for(Edge e: edgesFromMainVertex){
				for(Vertice v:e.getAllVertices()){
					if(v.equals(vertexB)){
						edgeMainB=e.getValue();
					}
				}	
			}
			//System.out.println(edgeMainB);
			//divide edgeMain by sumAllEdges
			//System.out.println("div: "+(edgeMainB/sumAllEdges));
			return edgeMainB/sumAllEdges;
		}
	
	public void calculateTMPPageRank(){
		double tmpPageRank=0.0;
		for(Vertice v:this.connectingVertices){
//			System.out.println(division(v)*(1.0/v.getEdges().size()));
			tmpPageRank+=division(v)*(1.0/v.getEdges().size());
			//System.out.println("div:"+division(v)+" * size:"+(1.0/v.getEdges().size())+"="+tmpPageRank);
			
		}
		
		this.main_vetex.setTmpPageRank((1-d)+d*tmpPageRank);
		//System.out.println("tmpPR: "+this.main_vetex.getTmpPageRank());
		
	}
	/**
	 * Use this method only if you already calculated the tmpRageRannk for all your Vetices.
	 * You can do this by calling the calculatingTMPPageRank() method.
	 * This method calculates the actual PageRank for one Vertice
	 */
	public void calculatePageRank(){
		double pageRank=0.0;
		for(Vertice v:this.connectingVertices){
			pageRank+=division(v)*(v.getTmpPageRank());
		}
		//richtige rechnung fehlt noch
		this.main_vetex.setPageRank((1-d)+d*pageRank);
		//System.out.println(pageRank);
		
	}
		
	

}
