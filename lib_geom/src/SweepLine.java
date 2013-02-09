import java.util.*;

public class SweepLine {
	
	Polygon pn;
	TreeSet<SLSegment> tree = new TreeSet<SLSegment>();
	
	public SweepLine(Polygon p) {
		pn = p;
	}
	
	SLSegment add(Event e) {
		SLSegment s = new SLSegment();
		s.edge = e.edge;
		Point v1 = pn.vertices[s.edge];
		Point v2 = pn.vertices[(s.edge+1)%pn.N];
		if (v1.compareTo(v2) < 0) { // determine the leftmost
			s.lp = v1;
			s.rp = v2;
		} else {
			s.lp = v2;
			s.rp = v1;
		}
		SLSegment nx, np;
		tree.add(s);
		nx = tree.higher(s);
		np = tree.lower(s);
		if (nx != null) {
			s.above = nx;
			s.above.below = s;
		}
		if (np != null) {
			s.below = np;
			s.below.above = s;
		}
		return s;
	}
	
	SLSegment find(Event e) {
		SLSegment s = new SLSegment();
		s.edge = e.edge;
		s.above = tree.higher(s);
		if (s.above != null)
			return s.above.below;
		s.below = tree.lower(s);
		if (s.below != null)
			return s.below.above;
		Point v1 = pn.vertices[s.edge];
		Point v2 = pn.vertices[(s.edge+1)%pn.N];
		if (v1.compareTo(v2) < 0) { // determine the leftmost
			s.lp = v1;
			s.rp = v2;
		} else {
			s.lp = v2;
			s.rp = v1;
		}
		return s;
	}
	
	void remove(SLSegment s) {
		if (s.above != null)
			s.above.below = s.below;
		if (s.below != null)
			s.below.above = s.above;
		tree.remove(s);
	}
	
	static class SLSegment implements Comparable<SLSegment> {
		
		int edge;
		Point lp;
		Point rp;
		SLSegment above;
		SLSegment below;
		
		public int compareTo(SLSegment s) {
			int c = lp.compareTo(s.lp);
			if (c != 0)
				return c;
			return rp.compareTo(s.rp);
		}
		
		boolean intersect(SLSegment s, int nv) {
			if (s == null)
				return false;
			int e1 = edge;
			int e2 = s.edge;
			if ((e1+1)%nv == e2 || e1 == (e2+1)%nv)
				return false; // consecutive
			double lsign = new Triangle(lp, rp, s.lp).isLeft();
			double rsign = new Triangle(lp, rp, s.rp).isLeft();
			if (lsign*rsign > 0)
				return false; // on same side
			lsign = new Triangle(s.lp, s.rp, lp).isLeft();
			rsign = new Triangle(s.lp, s.rp, rp).isLeft();
			if (lsign*rsign > 0)
				return false; // on same side
			return true;
		}
	}
	
	static class EventQueue {
		
		static int LEFT = 0;
		static int RIGHT = 1;
		
		Event[] edata;
		Event[] eq;
		int ix = 0;
		
		public EventQueue(Polygon p) {
			edata = new Event[2*p.N];
			eq = new Event[2*p.N];
			int i;
			for (i = 0; i < p.N; i++) {
				eq[2*i] = new Event(i, 0, p.vertices[i]);
				eq[2*i+1] = new Event(i, 0, p.vertices[(i+1)%p.N]);
				if (p.vertices[i].compareTo(p.vertices[(i+1)%p.N]) < 0) {
					eq[2*i].type = LEFT;
					eq[2*i+1].type = RIGHT;
				} else {
					eq[2*i].type = RIGHT;
					eq[2*i+1].type = LEFT;
				}
			}
			Arrays.sort(eq);
		}
		
		Event next() {
			if (ix >= eq.length)
				return null;
			return eq[ix++];
		}
	}
	
	static class Event implements Comparable<Event> {
		
		int edge; // polygon edge is v[i] to v[i+1]
		int type;
		Point eV; // event vertex
		
		Event(int e, int t, Point _ev) {
			edge = e;
			type = t;
			eV = _ev;
		}
		
		public int compareTo(Event e) {
			return eV.compareTo(e.eV);
		}
	}

}
