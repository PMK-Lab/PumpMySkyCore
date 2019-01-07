package pumpmyskycore.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import fr.pumpmyskycore.Plot;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotInvited;
import fr.pumpmyskycore.exceptions.RestrictActionToPlotOwnerException;
import pumpmyskycore.utils.FakePlayer;
import pumpmyskycore.utils.TestPlotManager;

public class PlotManagerPlayerInvitesActionTest {

	@Test
	public void playerInviteIsland() throws IOException, InvalidConfigurationException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerAlreadyHavePlotException {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot island = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
	}
	
	@Test
	public void playerUninviteIsland() throws IOException, InvalidConfigurationException, PlayerAlreadyHavePlotException, PlayerDoesNotHavePlotException, RestrictActionToPlotOwnerException, PlayerAlreadyInvited, PlayerDoesNotInvited {
		
		TestPlotManager manager = TestPlotManager.initManager(this.getClass());
		
		FakePlayer invitor = new FakePlayer(UUID.randomUUID());		
		FakePlayer target = new FakePlayer(UUID.randomUUID());
		
		Plot island = manager.playerCreatePlot(invitor);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
		manager.playerInvitePlot(invitor, target);
		
		assertTrue(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
		manager.playerUninvitePlot(invitor, target);
		
		assertFalse(manager.getPlotInvites().isInvites(target.getUuid(), island));
		
	}
	
	public void playerAcceptInviteIsland() {
		
		
		
	}
	
	public void playerRefuseInviteIsland() {
		
	}
	
}