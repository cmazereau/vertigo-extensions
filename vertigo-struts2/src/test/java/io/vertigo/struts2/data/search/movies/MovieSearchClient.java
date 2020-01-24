package io.vertigo.struts2.data.search.movies;

import java.util.Arrays;

import javax.inject.Inject;

import io.vertigo.commons.transaction.VTransactionManager;
import io.vertigo.core.lang.Generated;
import io.vertigo.core.node.Home;
import io.vertigo.core.node.component.Component;
import io.vertigo.core.util.InjectorUtil;
import io.vertigo.datafactory.collections.ListFilter;
import io.vertigo.datafactory.collections.metamodel.FacetedQueryDefinition;
import io.vertigo.datafactory.collections.metamodel.ListFilterBuilder;
import io.vertigo.datafactory.collections.model.FacetedQueryResult;
import io.vertigo.datafactory.collections.model.SelectedFacetValues;
import io.vertigo.datafactory.search.SearchManager;
import io.vertigo.datafactory.search.metamodel.SearchIndexDefinition;
import io.vertigo.datafactory.search.model.SearchQuery;
import io.vertigo.datafactory.search.model.SearchQueryBuilder;
import io.vertigo.dynamo.domain.model.DtListState;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.struts2.data.domain.movies.Movie;
import io.vertigo.struts2.data.domain.movies.MovieIndex;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
@io.vertigo.datafactory.search.metamodel.annotation.SearchIndexAnnotation(name = "IdxMovie", dtIndex = "DtMovieIndex", keyConcept = "DtMovie", loaderId = "MovieSearchLoader")
public final class MovieSearchClient implements Component {

	private final SearchManager searchManager;
	private final VTransactionManager transactionManager;

	/**
	 * Contructeur.
	 * @param searchManager Search Manager
	 * @param transactionManager Transaction Manager
	 */
	@Inject
	public MovieSearchClient(final SearchManager searchManager, final VTransactionManager transactionManager) {
		this.searchManager = searchManager;
		this.transactionManager = transactionManager;
	}

	/**
	 * Création d'une SearchQuery de type : Movie.
	 * @param criteria Critères de recherche
	 * @param selectedFacetValues Liste des facettes sélectionnées à appliquer
	 * @return SearchQueryBuilder pour ce type de recherche
	 */
	@io.vertigo.datafactory.search.metamodel.annotation.FacetedQueryAnnotation(
			name = "QryMovie",
			keyConcept = "DtMovie",
			listFilterBuilderClass = io.vertigo.dynamox.search.DslListFilterBuilder.class,
			listFilterBuilderQuery = "_all:#+query*#",
			criteriaSmartType = "STyLabel",
			facets = {
					@io.vertigo.datafactory.search.metamodel.annotation.Facet(
							type = "term",
							name = "FctMovieType$qryMovie",
							dtDefinition = "DtMovieIndex",
							fieldName = "movieType",
							label = "Par type",
							order = io.vertigo.datafactory.collections.metamodel.FacetDefinition.FacetOrder.count),
					@io.vertigo.datafactory.search.metamodel.annotation.Facet(
							type = "range",
							name = "FctMovieTitle$qryMovie",
							dtDefinition = "DtMovieIndex",
							fieldName = "titleSortOnly",
							label = "Par titre",
							order = io.vertigo.datafactory.collections.metamodel.FacetDefinition.FacetOrder.definition,
							ranges = {
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r1", filter = "titleSortOnly:[* TO a]", label = "#"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r2", filter = "titleSortOnly:[a TO g]", label = "a-f"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r3", filter = "titleSortOnly:[g TO n]", label = "g-m"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r4", filter = "titleSortOnly:[n TO t]", label = "n-s"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r5", filter = "titleSortOnly:[t TO *]", label = "t-z") }),
					@io.vertigo.datafactory.search.metamodel.annotation.Facet(
							type = "range",
							name = "FctMovieYear$qryMovie",
							dtDefinition = "DtMovieIndex",
							fieldName = "productionYear",
							label = "Par date",
							order = io.vertigo.datafactory.collections.metamodel.FacetDefinition.FacetOrder.definition,
							ranges = {
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r1", filter = "productionYear:[* TO 1930]", label = "< années 30"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r2", filter = "productionYear:[1930 TO 1940]", label = "années 30"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r3", filter = "productionYear:[1940 TO 1950]", label = "années 40"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r4", filter = "productionYear:[1950 TO 1960]", label = "années 50"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r5", filter = "productionYear:[1960 TO 1970]", label = "années 60"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r6", filter = "productionYear:[1970 TO 1980]", label = "années 70"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r7", filter = "productionYear:[1980 TO 1990]", label = "années 80"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r8", filter = "productionYear:[1990 TO 2000]", label = "années 90"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r9", filter = "productionYear:[2000 TO 2010]", label = "années 2000"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r10", filter = "productionYear:[2010 TO *]", label = "> années 2010") })
			})
	public SearchQueryBuilder createSearchQueryBuilderMovie(final java.lang.String criteria, final SelectedFacetValues selectedFacetValues) {
		final FacetedQueryDefinition facetedQueryDefinition = Home.getApp().getDefinitionSpace().resolve("QryMovie", FacetedQueryDefinition.class);
		final ListFilterBuilder<java.lang.String> listFilterBuilder = InjectorUtil.newInstance(facetedQueryDefinition.getListFilterBuilderClass());
		final ListFilter criteriaListFilter = listFilterBuilder.withBuildQuery(facetedQueryDefinition.getListFilterBuilderQuery()).withCriteria(criteria).build();
		return SearchQuery.builder(criteriaListFilter).withFacet(facetedQueryDefinition, selectedFacetValues);
	}

	/**
	 * Création d'une SearchQuery de type : MovieWithPoster.
	 * @param criteria Critères de recherche
	 * @param selectedFacetValues Liste des facettes sélectionnées à appliquer
	 * @return SearchQueryBuilder pour ce type de recherche
	 */
	@io.vertigo.datafactory.search.metamodel.annotation.FacetedQueryAnnotation(
			name = "QryMovieWithPoster",
			keyConcept = "DtMovie",
			listFilterBuilderClass = io.vertigo.dynamox.search.DslListFilterBuilder.class,
			listFilterBuilderQuery = "_all:#+query*# +_exists_:poster",
			criteriaSmartType = "STyLabel",
			facets = {
					@io.vertigo.datafactory.search.metamodel.annotation.Facet(
							type = "term",
							name = "FctMovieType$qryMovieWithPoster",
							dtDefinition = "DtMovieIndex",
							fieldName = "movieType",
							label = "Par type",
							order = io.vertigo.datafactory.collections.metamodel.FacetDefinition.FacetOrder.count),
					@io.vertigo.datafactory.search.metamodel.annotation.Facet(
							type = "range",
							name = "FctMovieTitle$qryMovieWithPoster",
							dtDefinition = "DtMovieIndex",
							fieldName = "titleSortOnly",
							label = "Par titre",
							order = io.vertigo.datafactory.collections.metamodel.FacetDefinition.FacetOrder.definition,
							ranges = {
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r1", filter = "titleSortOnly:[* TO a]", label = "#"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r2", filter = "titleSortOnly:[a TO g]", label = "a-f"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r3", filter = "titleSortOnly:[g TO n]", label = "g-m"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r4", filter = "titleSortOnly:[n TO t]", label = "n-s"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r5", filter = "titleSortOnly:[t TO *]", label = "t-z") }),
					@io.vertigo.datafactory.search.metamodel.annotation.Facet(
							type = "range",
							name = "FctMovieYear$qryMovieWithPoster",
							dtDefinition = "DtMovieIndex",
							fieldName = "productionYear",
							label = "Par date",
							order = io.vertigo.datafactory.collections.metamodel.FacetDefinition.FacetOrder.definition,
							ranges = {
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r1", filter = "productionYear:[* TO 1930]", label = "< années 30"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r2", filter = "productionYear:[1930 TO 1940]", label = "années 30"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r3", filter = "productionYear:[1940 TO 1950]", label = "années 40"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r4", filter = "productionYear:[1950 TO 1960]", label = "années 50"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r5", filter = "productionYear:[1960 TO 1970]", label = "années 60"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r6", filter = "productionYear:[1970 TO 1980]", label = "années 70"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r7", filter = "productionYear:[1980 TO 1990]", label = "années 80"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r8", filter = "productionYear:[1990 TO 2000]", label = "années 90"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r9", filter = "productionYear:[2000 TO 2010]", label = "années 2000"),
									@io.vertigo.datafactory.search.metamodel.annotation.Range(code = "r10", filter = "productionYear:[2010 TO *]", label = "> années 2010") })
			})
	public SearchQueryBuilder createSearchQueryBuilderMovieWithPoster(final java.lang.String criteria, final SelectedFacetValues selectedFacetValues) {
		final FacetedQueryDefinition facetedQueryDefinition = Home.getApp().getDefinitionSpace().resolve("QryMovieWithPoster", FacetedQueryDefinition.class);
		final ListFilterBuilder<java.lang.String> listFilterBuilder = InjectorUtil.newInstance(facetedQueryDefinition.getListFilterBuilderClass());
		final ListFilter criteriaListFilter = listFilterBuilder.withBuildQuery(facetedQueryDefinition.getListFilterBuilderQuery()).withCriteria(criteria).build();
		return SearchQuery.builder(criteriaListFilter).withFacet(facetedQueryDefinition, selectedFacetValues);
	}

	/**
	 * Récupération du résultat issu d'une requête.
	 * @param searchQuery critères initiaux
	 * @param listState Etat de la liste (tri et pagination)
	 * @return Résultat correspondant à la requête (de type MovieIndex)
	 */
	public FacetedQueryResult<MovieIndex, SearchQuery> loadList(final SearchQuery searchQuery, final DtListState listState) {
		final SearchIndexDefinition indexDefinition = searchManager.findFirstIndexDefinitionByKeyConcept(Movie.class);
		return searchManager.loadList(indexDefinition, searchQuery, listState);
	}

	/**
	 * Mark an entity as dirty. Index of these elements will be reindexed if Tx commited.
	 * Reindexation isn't synchrone, strategy is dependant of plugin's parameters.
	 *
	 * @param entityUID Key concept's UID
	 */
	public void markAsDirty(final UID<Movie> entityUID) {
		transactionManager.getCurrentTransaction().addAfterCompletion((final boolean txCommitted) -> {
			if (txCommitted) {// reindex only is tx successful
				searchManager.markAsDirty(Arrays.asList(entityUID));
			}
		});
	}

	/**
	 * Mark an entity as dirty. Index of these elements will be reindexed if Tx commited.
	 * Reindexation isn't synchrone, strategy is dependant of plugin's parameters.
	 *
	 * @param entity Key concept
	 */
	public void markAsDirty(final Movie entity) {
		markAsDirty(UID.of(entity));
	}
}