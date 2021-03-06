package com.mynetpcb.core.board;

import com.mynetpcb.core.capi.ViewportWindow;
import com.mynetpcb.core.pad.Net;

import com.mynetpcb.core.pad.shape.PadShape;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;


/*
 * Copper area is clearance initiator
 */
public interface ClearanceSource extends Clearanceaware,Net{

   /*
    * Clipping region to isolate clearance drawing into
    */
   public Polygon getClippingRegion(ViewportWindow viewportWindow,AffineTransform scale);

   public PadShape.PadConnection getPadConnection(); 
   
   public boolean isSelected();
}
