package me.skylertyler.scrimmage.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener{
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player eventPlayer = (Player)event.getEntity();
		Player killer = eventPlayer.getKiller();
		switch(eventPlayer.getLastDamageCause().getCause()){
		case BLOCK_EXPLOSION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" explotó gracias a la TNT de "+killer.getDisplayName());
			break;
		case CONTACT:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" fue asesinado por "+killer.getDisplayName());
			break;
		case CUSTOM:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió");
			break;
		case DROWNING:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió ahogado");
			break;
		case ENTITY_ATTACK:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió por las garras de "+killer.getDisplayName());
			break;
		case ENTITY_EXPLOSION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió");
			break;
		case FALL:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" olvidó como caminar");
			break;
		case FALLING_BLOCK:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió de dolor de cabeza");
			break;
		case FIRE:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió calcinado");
			break;
		case FIRE_TICK:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se convirtió en cenizas");
			break;
		case LAVA:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se dio un baño caliente");
			break;
		case LIGHTNING:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" quería ser Thor");
			break;
		case MAGIC:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" sintió el avada kedavra de "+killer.getDisplayName());
			break;
		case MELTING:
			break;
		case POISON:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" intentó agarrar la poción de "+killer.getDisplayName());
			break;
		case PROJECTILE:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" fue disparado hasta morir por "+killer.getDisplayName());
			break;
		case STARVATION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" olvidó comer ");
			break;
		case SUFFOCATION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" olvidó respirar ");
			break;
		case SUICIDE:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se suicidó");
			break;
		case THORNS:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" sintió las espinas del enemigo");
			break;
		case VOID:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se cayó del mundo");
			break;
		case WITHER:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" fue withereado");
			break;
		default:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" murió por razones desconocidas");
			break;

		}
	}

}
