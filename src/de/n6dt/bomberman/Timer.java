package de.n6dt.bomberman;

public class Timer {
	private float _timer;
	private float _start;
	private float _duration;

	public Timer(int start, int duration) {
		_start = start;
		_duration = duration;
	}

	public float getTimer() {
		return _timer;
	}

	public void setTimer(float _timer) {
		this._timer = _timer;
	}

	public float getTimeStart() {
		return _start;
	}

	public void setTimeStart(float _timeStart) {
		this._start = _timeStart;
	}

	public float getDuration() {
		return _duration;
	}

	public void setDuration(float duration) {
		this._duration = duration;
	}
	
	public void tick() {
		
		
	}
	
	public boolean finished() {
		return false;
	}
}