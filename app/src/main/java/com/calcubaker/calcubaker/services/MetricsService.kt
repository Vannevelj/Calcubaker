package com.calcubaker.Calcubaker.services

import com.calcubaker.Calcubaker.models.*
import com.calcubaker.Calcubaker.repositories.ConversionRepository
import com.calcubaker.Calcubaker.repositories.MetricsRepository
import com.calcubaker.Calcubaker.repositories.ProductRepository
import no.wtw.android.dijkstra.DijkstraAlgorithm
import no.wtw.android.dijkstra.model.Edge
import no.wtw.android.dijkstra.model.Graph
import no.wtw.android.dijkstra.model.Vertex
import java.lang.Integer.parseInt

class MetricsService(
    private val metricsRepository: MetricsRepository,
    private val conversionRepository: ConversionRepository,
    private val productRepository: ProductRepository) {

    fun getMetrics() : List<Metric> = metricsRepository.getMetrics()
    fun getProducts() : List<Product> = productRepository.getProducts().sortedBy { product -> product.name }
    fun getMetric(metricUnit: MetricUnit) : Metric = metricsRepository.getMetrics().first { metric -> metric.id == metricUnit}

    private fun getConversion(source: Metric, destination: Metric) : Conversion {
        val conversions = conversionRepository.getConversions()
        val conversion = conversions.find { c -> c.from == source.id && c.to == destination.id }

        return conversion ?: getChainedConversions(source, destination)
    }

    private fun getChainedConversions(source: Metric, destination: Metric) : Conversion {
        val conversions = conversionRepository.getConversions()

        val edges = arrayListOf<Edge>()
        conversions.forEach { conversion ->
            edges.add(Edge(Vertex(conversion.from.id), Vertex(conversion.to.id), 1))
        }

        val graph = Graph(edges)
        val dijkstra = DijkstraAlgorithm(graph)
        val sourceVertex = dijkstra.execute(Vertex(source.id.id))
        val path = sourceVertex.getPath(Vertex(destination.id.id))

        val chainedConversions = arrayListOf<Conversion>()
        var index = 1
        var start = parseInt((path[0].payload.toString()))
        var end: Int

        do
        {
            end = parseInt((path[index].payload.toString()))

            var conversion = conversions.first { conv -> conv.from.id == start && conv.to.id == end }
            chainedConversions.add(conversion)

            index++;
            start = end;
        } while (chainedConversions.last().to.id != destination.id.id)

        return ComposedConversion(source.id, destination.id, chainedConversions)
    }

    fun calculate(amount: Double, product: Product, source: Metric, destination: Metric) : Double {
        if (source.id == destination.id) { return amount }

        val conversion = getConversion(source, destination)

        return conversion.calculate(amount, product)
    }
}