package tasks;

import main.MainClass;
import manager.TrailsManager;
import objects.Trail;

public class TrailsTask implements Runnable {

//	public Iterator<Entry<Player, Trail>> toIterator() {
//		return MainClass.getMainClass().getTrailsManager().getPlayerwhoSeletedTrail().entrySet().iterator();
//	}
//
//	@Override
//	public void run() {
//		
//		try {
//			Iterator<Entry<Player, Trail>> iterator = toIterator();
//			while (iterator.hasNext()) {
//				Entry<Player, Trail> entry = iterator.next();
//				Trail trail = entry.getValue();
//				MainClass.getMainClass().getTrailsManager().displayParticle(trail.getTrailParticlesEnum().getEffect());
//			}
//		} catch (ConcurrentModificationException e) {
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public void run() {
		TrailsManager trailsManager = MainClass.getMainClass().getTrailsManager();

		for (Trail trail : trailsManager.getPlayerwhoSeletedTrail().values()) {
			trailsManager.displayParticle(trail.getTrailParticlesEnum().getEnumParticle());
		}
	}

}
