package edu.ufl.cise.cs1.controllers;
import game.controllers.AttackerController;
import game.models.*;
import java.util.ArrayList;
import java.util.List;

public final class StudentAttackerController implements AttackerController
{
	private enum Range
	{
		// Do NOT change values here, change them within the init method down below.
		DANGER(14), POWERPILL(35), KILL(27);
		private int value;

		Range(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
		}
	}

	public void init(Game game)
	{
		// Adjusts the distance the gator will get close to NON-vulnerable defenders
		Range.DANGER.setValue(14);
		// Adjusts the distance the gator will sense vulnerable defenders
		Range.KILL.setValue(27);
		// Adjusts the distance the gator will go to a power pill when within DANGER range of a Non-vulnerable defender
		Range.POWERPILL.setValue(30);
	}

	public void shutdown(Game game)
	{

	}

	public int update(Game game,long timeDue)
	{
		// The attacker
		Attacker gator = game.getAttacker();

		List<Node> pills = game.getPillList();				// List of ALL pills (Used and NOT used)
		List<Node> powerPills = game.getPowerPillList();	// List of ALL power pills (Used and NOT used)
		List<Defender> defenders = game.getDefenders();		// List of ALL defenders

		// Loop through all pills
		for (int i = 0; i < pills.size(); i++)
		{
			// Pill has been eaten already
			if (!game.checkPill(pills.get(i)))
			{
				// Remove it from the pill list
				pills.remove(i);
			}
		}

		// Loop through all power pills
		for (int i = 0; i < powerPills.size(); i++)
		{
			// Power pill has been eaten already
			if (!game.checkPowerPill(powerPills.get(i)))
			{
				// Remove it from the power pill list
				powerPills.remove(i);
			}
		}

		// List of NON-vulnerable defenders within DANGER range
		List<Defender> dangerousDefenders = dangerousDefenders(game, Range.DANGER.getValue());

		// There are dangerous defenders within DANGER range
		if (dangerousDefenders.size() != 0)
		{
			// Power pills are available
			if (powerPills.size() != 0)
			{
				Node nearestPowerPill;

				// Loop while power pills are available (a power pill is removed when the path to it is deemed unsafe)
				while (powerPills.size() > 0)
				{
					nearestPowerPill = game.getAttacker().getTargetNode(powerPills, true);

					// Power pill is within POWERPILL range
					if (nearestPowerPill.getPathDistance(gator.getLocation()) <= Range.POWERPILL.getValue())
					{
						boolean safePath = true;

						// Loop through the amounts of dangerous defenders
						for(int i = 0; i < dangerousDefenders.size(); i++)
						{
							// Path to power pill includes defender
							if (gator.getNextDir(dangerousDefenders.get(i).getLocation(), true) == gator.getNextDir(nearestPowerPill, true))
							{
								// Mark safe path as false
								safePath = false;
							}
						}

						// Safe path has no defenders
						if(safePath)
						{
							// Go to the nearest power pill
							return gator.getNextDir(nearestPowerPill, true);
						}
					}

					// Nearest power pill is not safe, remove it from powerPills list
					powerPills.remove(nearestPowerPill);
				}
			}
			// Pills are available
			if (pills.size() != 0)
			{
				Node nearestPill;

				// Loop while pills are available (a pill is removed when the path to it is deemed unsafe)
				while (pills.size() > 0)
				{
					nearestPill = game.getAttacker().getTargetNode(pills, true);
					boolean safePath = true;

					// Loop through the amounts of dangerous defenders
					for(int i = 0; i < dangerousDefenders.size(); i++)
					{
						// Path to pill includes defender
						if (gator.getNextDir(dangerousDefenders.get(i).getLocation(), true) == gator.getNextDir(nearestPill, true))
						{
							// Mark safe path as false
							safePath = false;
						}
					}

					// Safe path has no defenders
					if(safePath)
					{
						// Go for pill
						return gator.getNextDir(nearestPill, true);
					}

					// Nearest pill is not safe, remove it from pills list
					pills.remove(nearestPill);
				}
			}

			// Stores all directions with defenders
			List<Integer> dangerousDirections = new ArrayList<Integer>();
			// Stores all possible directions
			List<Integer> possibleDirections = game.getAttacker().getPossibleDirs(true);

			// Loop through the amounts of dangerous defenders
			for(int i = 0; i < dangerousDefenders.size(); i++)
			{
				// Add direction-to-defender to dangerousDirections list
				dangerousDirections.add(gator.getNextDir(dangerousDefenders.get(i).getLocation(), true));
			}
			// Loop through the amounts of dangerous directions
			for(int i = 0; i < dangerousDirections.size(); i++)
			{
				// possibleDirections contains a dangerous direction
				if(possibleDirections.contains(dangerousDirections.get(i)))
				{
					// Remove the dangerous direction from possibleDirections
					possibleDirections.remove(dangerousDirections.get(i));
				}
			}

			// possibleDirections has at least one safe path
			if(possibleDirections.size() != 0)
			{
				return possibleDirections.get(0);
			}
			// No safe path exists
			else
			{
				// Stand there menacingly
				return -1;
			}
		}
		// No dangerous defenders within DANGER range
		else
		{
			Defender nearestDefender = (Defender) gator.getTargetActor(defenders, true);

			// Nearest defender is vulnerable and within KILL range
			if(nearestDefender.isVulnerable() && getDistance(gator, nearestDefender) <= Range.KILL.getValue())
			{
				// Go to it
				return gator.getNextDir(nearestDefender.getLocation(), true);
			}
			// Pills are available
			else if(pills.size() != 0)
			{
				// Go to the nearest pill
				return gator.getNextDir(game.getAttacker().getTargetNode(pills, true), true);
			}
			// Power pills are available
			else if(powerPills.size() != 0)
			{
				// Go to the nearest power pill
				return gator.getNextDir(game.getAttacker().getTargetNode(powerPills, true), true);
			}
			// No danger, no power pill, no pill, just you and me!
			else
			{
				return -1;
			}
		}
	}

	// Helper function to make code easier to write/understand
	// Returns distance between both actors.
	private int getDistance(Actor A, Actor B)
	{
		return A.getLocation().getPathDistance(B.getLocation());
	}

	// Helper function to make code easier to write/understand
	// Returns distance between both actor and node.
	private int getDistance(Actor A, Node B)
	{
		return A.getLocation().getPathDistance(B);
	}

	// Returns a list of defenders in range that are not vulnerable
	private List<Defender> dangerousDefenders(Game game, int range)
	{
		List<Defender> defenders = new ArrayList<Defender>();

		// Go through each defender
		for(int i = 0; i < game.getDefenders().size(); i++)
		{
			// Defender is NOT vulnerable and is within range
			if(!game.getDefender(i).isVulnerable() && getDistance(game.getAttacker(), game.getDefender(i)) <= range)
			{
				// Defender is not in lair
				if((game.getDefender(i).getLairTime() <= 0))
				{
					// Add to dangerous list
					defenders.add(game.getDefender(i));
				}
			}
		}

		return defenders;
	}
}