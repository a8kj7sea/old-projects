package enums;

import org.bukkit.Effect;

import net.minecraft.server.v1_8_R3.EnumParticle;

public enum TrailParticlesEnum {

	HEART(EnumParticle.HEART), SMOKE(EnumParticle.SMOKE_NORMAL), CLOUD(EnumParticle.CLOUD);

	private EnumParticle enumParticle;

	private TrailParticlesEnum(EnumParticle enumParticle) {
		this.enumParticle = enumParticle;
	}

	public EnumParticle getEnumParticle() {
		return this.enumParticle;
	}
}
