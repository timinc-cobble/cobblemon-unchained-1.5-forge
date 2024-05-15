package us.timinc.mc.cobblemon.chaining

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory
import net.minecraftforge.event.server.ServerStartedEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import us.timinc.mc.cobblemon.chaining.config.HiddenBoostConfig
import us.timinc.mc.cobblemon.chaining.config.IvBoostConfig
import us.timinc.mc.cobblemon.chaining.config.ShinyBoostConfig
import us.timinc.mc.cobblemon.chaining.influences.HiddenAbilityBooster
import us.timinc.mc.cobblemon.chaining.influences.IvBooster
import us.timinc.mc.cobblemon.chaining.influences.ShinyBooster
import us.timinc.mc.config.ConfigBuilder

@Mod(Chaining.MOD_ID)
object Chaining {
    @Suppress("MemberVisibilityCanBePrivate")
    const val MOD_ID = "unchained"

    private lateinit var shinyBoostConfig: ShinyBoostConfig
    private lateinit var ivBoostConfig: IvBoostConfig
    private lateinit var hiddenBoostConfig: HiddenBoostConfig

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    object Registration {
        @SubscribeEvent
        fun onInit(e: ServerStartedEvent) {
            shinyBoostConfig = ConfigBuilder.load(ShinyBoostConfig::class.java, "$MOD_ID/shinyBooster")
            ivBoostConfig = ConfigBuilder.load(IvBoostConfig::class.java, "$MOD_ID/ivBooster")
            hiddenBoostConfig = ConfigBuilder.load(HiddenBoostConfig::class.java, "$MOD_ID/haBooster")

            PlayerSpawnerFactory.influenceBuilders.add { ShinyBooster(it, shinyBoostConfig) }
            PlayerSpawnerFactory.influenceBuilders.add { HiddenAbilityBooster(it, hiddenBoostConfig) }
            PlayerSpawnerFactory.influenceBuilders.add { IvBooster(it, ivBoostConfig) }
        }
    }
}