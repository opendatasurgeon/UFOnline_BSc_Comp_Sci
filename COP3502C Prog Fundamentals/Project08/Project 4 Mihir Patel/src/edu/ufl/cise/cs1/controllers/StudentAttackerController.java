/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 *
 * Description: This project is a practice with inheritance and polymorphism in programming
 *              languages. "GatorRaider" is a game whose play and levels are based on Ms. Pac-Man. In this game, four defender
 *              characters (other lousy mascots) attempt to limit the damage by an attacker character (gator). This file is
 *              a controller for the attacking character.
 */

package edu.ufl.cise.cs1.controllers;
import game.controllers.AttackerController;
import game.models.*;
import java.awt.*;
import java.util.List;


public final class StudentAttackerController implements AttackerController
{

	public void init(Game game) { }

	public void shutdown(Game game) { }


	// at each run, move to a normal pill
	public static int move_to_a_pill(Game game, Node closestPill){
		// returns the direction attacker can make towards a closest pill
		return game.getAttacker().getNextDir(closestPill, true);
	}

	// Check which Defender is weaker. If none, return null, else return its index
	public static Integer check_weak_defender(Game game, List<Defender> lsy_mscts){

		//iterate over list of defenders, and check which one is weaker in every run
		for(int i = 0; i < lsy_mscts.size(); i++){
			if (game.getDefender(i).isVulnerable() || game.getDefender(i).getVulnerableTime() > 0){
				return i;
			}
		}
		//return null if all defenders are in their original state
		return null;
	}

	//check closest enemies from gator
	public static int check_defender_proximity(Actor gtr, List<Defender> lsy_mscts){

		//return the closest position of a defender from the gator/attacker
		return gtr.getLocation().getPathDistance(gtr.getTargetActor(lsy_mscts, true).getLocation());
	}

	public int update(Game game,long timeDue) {
		// Directions {UP: 0 RIGHT: 1 DOWN: 2 LEFT: 3 }

		int action; //stores the next gator movement

		//Get a copy of attacker's actor object
		Attacker gator = game.getAttacker();

		//Get the defenders' actor objects in a list
		List<Defender> lousy_mascots = game.getDefenders();

		//get list of junction nodes available in a list
		List<Node> junction_nodes =  game.getCurMaze().getJunctionNodes();

		//get the closest Node object gator needs to approach
		Node closestPill = gator.getTargetNode(game.getPillList(), true);

		//make the gator approach the closest pill
		action = move_to_a_pill(game, closestPill);

		//check if there are any weak defenders (happens when a powerpill gets eaten)
		Integer weak_mascots = check_weak_defender(game, lousy_mascots);

		//get an enemy out of 4 which is the weakest, if none then give a flag variable 9211 (to run away basically)
		int consume_this_mascots = (weak_mascots != null) ? weak_mascots : 9211;

		//Here we can update actions upon specific scenerios

		//(1): check if the defenders are 7 or more steps away from the attacker and the attacker's location has aPowerPill neighbor
		if (((check_defender_proximity(gator, lousy_mascots) >= 7)) &&
				(game.checkPowerPill(gator.getLocation().getNeighbor(action)))) {

			// If (1) is true then reverse the face of gator (let's enemy move in closer and allows easier gator attack)
			action = gator.getReverse();

		} else if (consume_this_mascots != 9211) {

			//(2): if there is/are weak defenders near attacker's location, approach them to consume
			action = gator.getNextDir(game.getDefender(consume_this_mascots).getLocation(), true);

		} else if ((check_defender_proximity(gator, lousy_mascots) > 0) && (check_defender_proximity(gator, lousy_mascots) < 5)) {
			// (3) : If there are invulnerable defenders within 0 to 5 steps from the attacker, correct the actions-

				try{
					// take all the possible movable locations, and take the nearest one
					action = gator.getNextDir(gator.getTargetNode(gator.getPossibleLocations(false), true), true);

				}catch (Exception e){

					try{
						// if above fails, get the neighboring nodes
						List<Node> gator_neighbors = closestPill.getNeighbors();

						//iterate through those neighboring nodes
						for (Node gator_neighbor : gator_neighbors) {

							// check if the node at i is a junction
							if (gator_neighbor.isJunction()) {

								//find the nearest junction node, and exist through there
								action = gator.getNextDir(gator.getTargetNode(junction_nodes, true), true);
								break;

							}
						}

					}catch (Exception ee){

						//if all fails, simply get away from the defender/s
						action = gator.getNextDir(gator.getTargetActor(lousy_mascots, true).getLocation(), false);

					}

				}

		}

		return action; // finally, return attacker's move
	}
}