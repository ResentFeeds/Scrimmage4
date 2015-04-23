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
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" explot� gracias a la TNT de "+killer.getDisplayName());
			break;
		case CONTACT:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" fue asesinado por "+killer.getDisplayName());
			break;
		case CUSTOM:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri�");
			break;
		case DROWNING:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri� ahogado");
			break;
		case ENTITY_ATTACK:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri� por las garras de "+killer.getDisplayName());
			break;
		case ENTITY_EXPLOSION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri�");
			break;
		case FALL:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" olvid� como caminar");
			break;
		case FALLING_BLOCK:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri� de dolor de cabeza");
			break;
		case FIRE:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri� calcinado");
			break;
		case FIRE_TICK:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se convirti� en cenizas");
			break;
		case LAVA:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se dio un ba�o caliente");
			break;
		case LIGHTNING:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" quer�a ser Thor");
			break;
		case MAGIC:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" sinti� el avada kedavra de "+killer.getDisplayName());
			break;
		case MELTING:
			break;
		case POISON:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" intent� agarrar la poci�n de "+killer.getDisplayName());
			break;
		case PROJECTILE:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" fue disparado hasta morir por "+killer.getDisplayName());
			break;
		case STARVATION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" olvid� comer ");
			break;
		case SUFFOCATION:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" olvid� respirar ");
			break;
		case SUICIDE:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se suicid�");
			break;
		case THORNS:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" sinti� las espinas del enemigo");
			break;
		case VOID:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" se cay� del mundo");
			break;
		case WITHER:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" fue withereado");
			break;
		default:
			event.setDeathMessage(eventPlayer.getDisplayName()+ChatColor.WHITE+" muri� por razones desconocidas");
			break;

		}
	}

}
