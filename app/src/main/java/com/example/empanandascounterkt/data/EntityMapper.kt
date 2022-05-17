package com.example.empanandascounterkt.data

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel?): Entity

    fun mapFromEntityList(entities: List<Entity>) : List<DomainModel> = entities.map { t -> mapFromEntity(t) }

    fun mapToEntityList(domainModel: List<DomainModel>) : List<Entity> = domainModel.map { t ->  mapToEntity(t) }

}