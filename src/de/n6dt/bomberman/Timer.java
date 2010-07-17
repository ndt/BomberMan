package de.n6dt.bomberman;

public class Timer {
	private float _start;
	private float _duration;

	public Timer(float duration) {
		_start = BomberMan.getP().frameCount;
		_duration = duration * BomberMan.getP().frameRate;
	}

	public float getTimeStart() {
		return _start;
	}

	public float getDuration() {
		return _duration;
	}

	public void setDuration(float duration) {
		this._duration = duration * BomberMan.getP().frameRate;
	}
	
	public boolean finished() {
		return BomberMan.getP().frameCount >= _start + _duration;
	}
}