package com.mynetpcb.board.shape;

import com.mynetpcb.board.unit.Board;
import com.mynetpcb.core.capi.Externalizable;
import com.mynetpcb.core.capi.shape.AbstractShapeFactory;
import com.mynetpcb.core.capi.shape.Shape;
import com.mynetpcb.core.capi.undo.AbstractMemento;
import com.mynetpcb.core.pad.Layer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BoardShapeFactory implements AbstractShapeFactory<Board,Shape>{{

}

    @Override
    public Shape createShape(Node node) {
        Element element=(Element)node;
        Shape shape=null;
        if(element.getTagName().equals("footprint")){
            shape = new PCBFootprint(0);            
        }
        if(element.getTagName().equals("label")){
            shape = new PCBLabel(0); 
        }   
        if(element.getTagName().equals("rectangle")){
             shape = new PCBRoundRect(0,0,0,0,0,0,0);
 
        }
        if(element.getTagName().equals("ellipse")){
             shape = new PCBCircle(0,0,0,0,0);
        }      
        if(element.getTagName().equals("arc")){
            shape = new PCBArc(0,0,0,0,0);
        }   
        if(element.getTagName().equals("wire")||element.getTagName().equals("track")){
            shape = new PCBTrack(0,0);  
        }   
        if(element.getTagName().equals("line")){
            shape = new PCBLine(0,Layer.SILKSCREEN_LAYER_FRONT);   
        }          
        if(element.getTagName().equals("via")){
            shape = new PCBVia();   
        }   
        if(element.getTagName().equals("hole")){
            shape = new PCBHole();   
        } 
        if(element.getTagName().equals("copperarea")){
            shape = new PCBCopperArea(0);   
        }  
        if(shape!=null){
            try {
                ((Externalizable) shape).fromXML(node);
            } catch (ParserConfigurationException | XPathExpressionException e) {
                 e.printStackTrace(System.out);
            }
        }
        return shape;
    }

    @Override
    public Shape createShape(Board board, AbstractMemento memento) {
        Shape shape=null;
        if(memento instanceof PCBFootprint.Memento){
            shape=new PCBFootprint(0);          
        }
        if(memento instanceof PCBTrack.Memento){
           shape=new PCBTrack(0,0);
        }
        if(memento instanceof PCBLine.Memento){
           shape=new PCBLine(0,Layer.SILKSCREEN_LAYER_FRONT);
        }
        if(memento instanceof PCBVia.Memento){
           shape=new PCBVia();  
        }
        if(memento instanceof PCBHole.Memento){
           shape=new PCBHole();  
        }
        if(memento instanceof PCBLabel.Memento){
           shape=new PCBLabel(0);  
        }
        if(memento instanceof PCBArc.Memento){
             shape = new PCBArc(0,0,0,0,0);
        }
        if(memento instanceof PCBCircle.Memento){
             shape=new PCBCircle( 0,0,0,0,0);
        }
        if(memento instanceof PCBRoundRect.Memento){
             shape=new PCBRoundRect(0,0,0,0,0,0,0);
        }
        if(memento instanceof PCBCopperArea.Memento){
             shape=new PCBCopperArea(0);
        }
        if(shape!=null){
            shape.setState(memento);
        }
        return shape;
    }
}
