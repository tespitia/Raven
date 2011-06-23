package raven.dddview;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.event.*;
import java.awt.AWTEvent;
import java.util.Enumeration;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

public class Universe extends Applet{

		public Geometry createTree(){
	        int counts[] = {19};
	        TriangleFanArray treeGeom = new TriangleFanArray(19,
	                                        GeometryArray.COORDINATES
	                                        | GeometryArray.COLOR_3,
	                                         counts);

	        treeGeom.setCoordinate( 0, new Point3f( 0.00f, 0.60f, 0.0f ));
	        treeGeom.setCoordinate( 1, new Point3f(-0.05f, 0.00f, 0.0f ));
	        treeGeom.setCoordinate( 2, new Point3f( 0.05f, 0.00f, 0.0f ));
	        treeGeom.setCoordinate( 3, new Point3f( 0.05f, 0.25f, 0.0f ));
	        treeGeom.setCoordinate( 4, new Point3f( 0.15f, 0.30f, 0.0f ));
	        treeGeom.setCoordinate( 5, new Point3f( 0.22f, 0.25f, 0.0f ));
	        treeGeom.setCoordinate( 6, new Point3f( 0.18f, 0.40f, 0.0f ));
	        treeGeom.setCoordinate( 7, new Point3f( 0.20f, 0.55f, 0.0f ));
	        treeGeom.setCoordinate( 8, new Point3f( 0.15f, 0.65f, 0.0f ));
	        treeGeom.setCoordinate( 9, new Point3f( 0.14f, 0.80f, 0.0f ));
	        treeGeom.setCoordinate(10, new Point3f( 0.08f, 0.95f, 0.0f ));
	        treeGeom.setCoordinate(11, new Point3f( 0.00f, 1.00f, 0.0f ));
	        treeGeom.setCoordinate(12, new Point3f(-0.20f, 0.85f, 0.0f ));
	        treeGeom.setCoordinate(13, new Point3f(-0.22f, 0.70f, 0.0f ));
	        treeGeom.setCoordinate(14, new Point3f(-0.30f, 0.60f, 0.0f ));
	        treeGeom.setCoordinate(15, new Point3f(-0.35f, 0.45f, 0.0f ));
	        treeGeom.setCoordinate(16, new Point3f(-0.25f, 0.43f, 0.0f ));
	        treeGeom.setCoordinate(17, new Point3f(-0.30f, 0.25f, 0.0f ));
	        treeGeom.setCoordinate(18, new Point3f(-0.02f, 0.24f, 0.0f ));

	        Color3f c = new Color3f(0.1f, 0.9f, 0.0f);
	        for(int i = 0; i < 19; i++) treeGeom.setColor(i, c);
	        c.set(0.5f, 0.5f, 0.3f);
	        treeGeom.setColor( 1, c);
	        treeGeom.setColor( 2, c);
	        treeGeom.setColor(18, c);

	        return treeGeom;
	    }

	    Shape3D createLand(){
	        LineArray landGeom = new LineArray(44, GeometryArray.COORDINATES
	                                            | GeometryArray.COLOR_3);
	        float l = -50.0f;
	        for(int c = 0; c < 44; c+=4){

	            landGeom.setCoordinate( c+0, new Point3f( -50.0f, 0.0f,  l ));
	            landGeom.setCoordinate( c+1, new Point3f(  50.0f, 0.0f,  l ));
	            landGeom.setCoordinate( c+2, new Point3f(   l   , 0.0f, -50.0f ));
	            landGeom.setCoordinate( c+3, new Point3f(   l   , 0.0f,  50.0f ));
	            l += 10.0f;
	        }

	        Color3f c = new Color3f(0.1f, 0.8f, 0.1f);
	        for(int i = 0; i < 44; i++) landGeom.setColor( i, c);

	        return new Shape3D(landGeom);
	    }

	    public BranchGroup createSceneGraph(SimpleUniverse su) {
		// Create the root of the branch graph
	        TransformGroup vpTrans = null;

	        BranchGroup objRoot = new BranchGroup();

	        Vector3f translate = new Vector3f();
	        Transform3D T3D = new Transform3D();
	        TransformGroup positionTG = null;
	        OrientedShape3D orientedShape3D = null;
	        BoundingSphere bSphere = new BoundingSphere();

	        objRoot.addChild(createLand());
	        
	        
	        float[][] wallCoordinates ={{0.0f, 10.0f, 50.0f},
	        					 {0.0f, 10.0f, -50.0f},
	        					 {50.0f, 10.0f, 0.0f},
	        					 {-50.0f, 10.0f, 0.0f} };
	        
	   
	        Appearance app = new Appearance();
	        Texture tex = new TextureLoader("tex.jpg", this).getTexture();
	        app.setTexture(tex);
	        
	    
	        //draws front/back walls
	        for(int i = 0; i < 2; i++)
	        {
	        	Box fb = new Box(50.0f, 10.0f, 1.0f, app);
		        translate.set(wallCoordinates[i]);
		        T3D.setTranslation(translate);
		        positionTG = new TransformGroup(T3D);
		        objRoot.addChild(positionTG);
		        positionTG.addChild(fb);
	        	
	        }
	        //draws wall at left/right
	        for(int i = 2; i < 4; i++)
	        {
	        	Box lr = new Box(1.0f, 10.0f, 50.0f, app);
		        translate.set(wallCoordinates[i]);
		        T3D.setTranslation(translate);
		        positionTG = new TransformGroup(T3D);
		        objRoot.addChild(positionTG);
		        positionTG.addChild(lr);
	        }
	      
	        


	        
	        
	        Geometry treeGeom = createTree();

	        //specify the position of the trees
	        float[][] position = {{  0.0f, 0.0f,  -2.0f},
	                              {  0.5f, 0.0f,  -3.0f},
	                              {  6.0f, 0.0f,   0.0f},
	                              {  6.5f, 0.0f,   6.0f},
	                              {  3.0f, 0.0f, -10.0f},
	                              { 13.5f, 0.0f, -30.0f},
	                              { 11.0f, 0.0f,   5.5f},
	                              {-08.5f, 0.0f,  10.5f},
	                              { 13.0f, 0.0f, -25.5f},
	                              {-13.5f, 0.0f,  30.5f},
	                              {-13.0f, 0.0f,  23.0f},
	                              {  1.0f, 0.0f,  -3.5f}};

	        // for the first N-1 positions in the array create a OS3D

	        Vector3f yAxis = new Vector3f(0.0f, 1.0f, 0.0f);

	        for (int i = 0; i < position.length-1; i++){
	                translate.set(position[i]);
	                T3D.setTranslation(translate);
	                positionTG = new TransformGroup(T3D);
	                orientedShape3D = new OrientedShape3D();
	                orientedShape3D.setGeometry(treeGeom);  // sharing geometry
	                objRoot.addChild(positionTG);
	                positionTG.addChild(orientedShape3D);
	        }

	        // one non-oriented tree for the last entry in the array
	        translate.set(position[position.length-1]);
	        T3D.setTranslation(translate);
	        positionTG = new TransformGroup(T3D);
	        objRoot.addChild(positionTG);
	        positionTG.addChild(new Shape3D(treeGeom));
	        
	        
	        ////////////////////////////////////////////TEST////////////////////////////////////////
	        
	        /*Appearance app = new Appearance();
	        Texture tex = new TextureLoader("tex.jpg", this).getTexture();
	        tex.setMagFilter(Texture.FASTEST);
	        app.setTexture(tex);
	        */
	        
	        translate.set(1.0f,2.0f,3.0f);
	        T3D.setTranslation(translate);
	        positionTG = new TransformGroup(T3D);
	        objRoot.addChild(positionTG);
	        Cylinder x = new Cylinder();
	        //x.setAppearance(app);
	        positionTG.addChild(x);
	        
	        translate.set(15.0f, 2.0f ,3.0f);
	        T3D.setTranslation(translate);
	        positionTG = new TransformGroup(T3D);
	        objRoot.addChild(positionTG);
	        positionTG.addChild(new Box());
	        
	        translate.set(7.0f, 2.0f ,10.0f);
	        T3D.setTranslation(translate);
	        positionTG = new TransformGroup(T3D);
	        objRoot.addChild(positionTG);
	        positionTG.addChild(new Sphere());
	        
	        translate.set(1.0f, 2.0f ,10.0f);
	        T3D.setTranslation(translate);
	        positionTG = new TransformGroup(T3D);
	        objRoot.addChild(positionTG);
	        positionTG.addChild(new Cone());
	        ////////////////////////////////////////////TEST////////////////////////////////////////

	        System.out.println(" geometry done");

	        // move eye point 'above the ground'
	        vpTrans = su.getViewingPlatform().getViewPlatformTransform();
	        translate.set( 0.0f, 2.0f, 0.0f);
	        T3D.setTranslation(translate);
	        vpTrans.setTransform(T3D);

	        // add key navigation ability
	        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(vpTrans);
	        keyNavBeh.setSchedulingBounds(new BoundingSphere(new Point3d(),1000.0));
	        objRoot.addChild(keyNavBeh);

	        // add background
	        Background background = new Background();
	        background.setColor(0.3f, 0.3f, 1.0f);
	        background.setApplicationBounds(new BoundingSphere(new Point3d(0.0f,0.0f,0.0f), 10.0));
	        objRoot.addChild(background);

		// Let Java 3D perform optimizations on this scene graph.
	        objRoot.compile();

	        return objRoot;
	    } // end of CreateSceneGraph method of OrientedShape3DApp

	    
	    public Universe() {
	        setLayout(new BorderLayout());
	        GraphicsConfiguration config =
	           SimpleUniverse.getPreferredConfiguration();

	        Canvas3D canvas3D = new Canvas3D(config);
	        add("Center", canvas3D);

	        // SimpleUniverse is a Convenience Utility class
	        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

	        BranchGroup scene = createSceneGraph(simpleU);

	        simpleU.addBranchGraph(scene);
	    } // end of OrientedShape3DApp (constructor)


	    //  The following allows this to be run as an application
	    //  as well as an applet

	    public static void main(String[] args) {
	        System.out.println("OrientedShape3DApp.java \n- a demonstration of the OrientedShape3D ");
	        System.out.println("class to provide automatic geometry orientation in a Java 3D scene.");
	        System.out.println("Each of the trees in the scene are 2D geometry.\n");
	        
	        System.out.println("When the app loads, you can use the arrow keys to move.");
	        System.out.println("No matter what direction you face, the trees will face you.");
	        System.out.println("There is one tree that does not automatically reorient. Can you find it?\n");

	        System.out.println("This is an example progam from The Java 3D API Tutorial.");
	        System.out.println("The Java 3D Tutorial is available on the web at:");
	        System.out.println("http://java.sun.com/products/java-media/3D/collateral");

	        System.out.print("\nOrientedShape3D bug (id: 4472252), fixed in the 1.2.1_02 release,");
	        System.out.println(" prevents this program from working correctly with older versions of the API.");
	        System.out.print("creating geometry: ....");
	        Frame frame = new MainFrame(new Universe(), 1024, 768);
	    } // end of main (method of OrientedShape3DApp)
	    
	}
