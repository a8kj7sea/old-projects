package objects;

import org.apache.commons.lang.Validate;

import enums.TrailParticlesEnum;

public class Trail {

	private TrailParticlesEnum trailParticlesEnum;

	public Trail(TrailParticlesEnum trailParticlesEnum) {
		setTrailParticlesEnum(trailParticlesEnum);
	}

	public Trail() {
		Validate.notNull(trailParticlesEnum);
		setTrailParticlesEnum(null);
	}

	public Trail setTrailParticlesEnum(TrailParticlesEnum trailParticlesEnum) {
		this.trailParticlesEnum = trailParticlesEnum;
		return this;
	}

	public TrailParticlesEnum getTrailParticlesEnum() {
		return this.trailParticlesEnum;
	}

}
