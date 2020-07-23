/**
 * 
 */
package br.com.desafio.tasklist.backend.service.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;

/**
 * @author jose-nery
 * 
 * Fornece as funções básicas de um Mapper
 *
 * @param <E>
 *            - Entidade
 * @param <T>
 *            - Dto            
 *            
 */
abstract class GenericMapper<E, T> {
	
	protected DozerBeanMapper dozer;
	
	private Class<T> clazzDto;
	
	private Class<E> clazzEntity;
	
	protected GenericMapper() {
		this.dozer = new DozerBeanMapper();
	}
	
	public T mapperEntity(E entidade) {
		return dozer.map(entidade, clazzDto);
	}
	
	public E mapperDtoToCreate(T dto) {

		return dozer.map(dto, clazzEntity);

	}

	public E mapperDtoToUpdate(T dto, E entidade) {

		entidade = dozer.map(dto, clazzEntity);
				
		return entidade;

	}
	
	public List<T> mapperListEntity(List<E> entidades) {

		return entidades.stream().map(item -> this.mapperEntity(item)).collect(Collectors.toList());

	}

}
