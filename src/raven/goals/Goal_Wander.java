package raven.goals;

import raven.game.RavenBot;

public class Goal_Wander extends Goal<RavenBot> {

	public Goal_Wander(RavenBot m_pOwner) {
		super(m_pOwner, Goal.GoalType.goal_wander);
	}

	@Override
	public void activate() {
		m_iStatus = Goal.CurrentStatus.active;
		m_pOwner.getSteering().wanderOn();

	}

	@Override
	public raven.goals.Goal.CurrentStatus process(double delta) {
		activateIfInactive();
		return m_iStatus;
	}


	@Override
	public void terminate() {
		m_pOwner.getSteering().wanderOff();
	}

	@Override
	public void render() {
		// do nothing
	}

}
