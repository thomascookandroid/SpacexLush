package com.lush.spacex.processing.mappers

import com.lush.spacex.persistance.entities.rocket.*
import com.lush.spacex.processing.interfaces.RocketMapper
import com.lush.spacex.remote.models.rocket.RocketDetail

class RocketMapperImpl : RocketMapper {
    override fun mapRemoteModelToPersistenceModel(rocket: RocketDetail) : RocketEntity {
        return RocketEntity(
            id = rocket.id,
            height = rocket.height.let { toMapSizeDim ->
                SizeDimension(
                    toMapSizeDim.meters ?: 0f,
                    toMapSizeDim.feet ?: 0f
                )
            },
            diameter = rocket.diameter.let { toMapSizeDim ->
                SizeDimension(
                    toMapSizeDim.meters ?: 0f,
                    toMapSizeDim.feet ?: 0f
                )
            },
            mass = rocket.mass.let { toMapMassDim ->
                MassDimension(
                    toMapMassDim.kg,
                    toMapMassDim.lb
                )
            },
            firstStage = rocket.firstStage.let { toMapFirstStage ->
                FirstStage(
                    ThrustDimension(
                        kN = toMapFirstStage.thrustSeaLevel.kN,
                        lbf = toMapFirstStage.thrustSeaLevel.lbf
                    ),
                    ThrustDimension(
                        kN = toMapFirstStage.thrustVacuum.kN,
                        lbf = toMapFirstStage.thrustVacuum.lbf
                    ),
                    toMapFirstStage.reusable,
                    toMapFirstStage.engines,
                    toMapFirstStage.fuelAmountTonnes,
                    toMapFirstStage.burnTimeSeconds ?: 0
                )
            },
            secondStage = rocket.secondStage.let { toMapSecondStage ->
                SecondStage(
                    ThrustDimension(
                        kN = toMapSecondStage.thrust.kN,
                        lbf = toMapSecondStage.thrust.lbf
                    ),
                    payloads = toMapSecondStage.payloads.let { toMapPayloads ->
                        Payloads(
                            compositeFairing = toMapPayloads.compositeFairing.let { toMapCompositeFairing ->
                                CompositeFairing(
                                    height = toMapCompositeFairing.height.let { toMapSizeDim ->
                                        SizeDimension(
                                            toMapSizeDim.meters ?: 0f,
                                            toMapSizeDim.feet ?: 0f
                                        )
                                    },
                                    diameter = toMapCompositeFairing.diameter.let { toMapSizeDim ->
                                        SizeDimension(
                                            toMapSizeDim.meters ?: 0f,
                                            toMapSizeDim.feet ?: 0f
                                        )
                                    }
                                )
                            },
                            optionOne = toMapPayloads.optionOne
                        )
                    },
                    reusable = toMapSecondStage.reusable,
                    engines = toMapSecondStage.engines,
                    fuelAmountTonnes = toMapSecondStage.fuelAmountTonnes,
                    burnTimeSeconds = toMapSecondStage.burnTimeSeconds ?: 0
                )
            },
            engines = rocket.engines.let { toMapEngines ->
                Engines(
                    specificImpulse = toMapEngines.specificImpulse.let { toMapImpulseDim ->
                        SpecificImpulseDimension(
                            seaLevel = toMapImpulseDim.seaLevel,
                            vacuum = toMapImpulseDim.vacuum
                        )
                    },
                    thrustSeaLevel = toMapEngines.thrustSeaLevel.let { toMapThrustDim ->
                        ThrustDimension(
                            kN = toMapThrustDim.kN,
                            lbf = toMapThrustDim.lbf
                        )
                    },
                    thrustVacuum = toMapEngines.thrustVacuum.let { toMapThrustDim ->
                        ThrustDimension(
                            kN = toMapThrustDim.kN,
                            lbf = toMapThrustDim.lbf
                        )
                    },
                    number = toMapEngines.number,
                    type = toMapEngines.type,
                    version = toMapEngines.version,
                    layout = toMapEngines.layout ?: "",
                    engineLossMax = toMapEngines.engineLossMax ?: 0,
                    propellantOne = toMapEngines.propellantOne,
                    propellantTwo = toMapEngines.propellantTwo,
                    thrustToWeight = toMapEngines.thrustToWeight
                )
            },
            landingLegs = rocket.landingLegs.let { toMapLandingLegs ->
                LandingLegs(
                    number = toMapLandingLegs.number,
                    material = toMapLandingLegs.material ?: ""
                )
            },
            payloadWeights = rocket.payloadWeights.map { toMapPayloadWeight ->
                PayloadWeight(
                    id = toMapPayloadWeight.id,
                    name = toMapPayloadWeight.name,
                    kg = toMapPayloadWeight.kg,
                    lb = toMapPayloadWeight.lb
                )
            },
            imageUrls = rocket.imageUrls,
            name = rocket.name,
            type = rocket.type,
            active = rocket.active,
            stages = rocket.stages,
            boosters = rocket.boosters,
            costPerLaunch = rocket.costPerLaunch,
            successRatePercent = rocket.successRatePercent,
            firstFlight = rocket.firstFlight,
            country = rocket.country,
            company = rocket.company,
            wikipediaUrl = rocket.wikipediaUrl,
            description = rocket.description
        )
    }
}