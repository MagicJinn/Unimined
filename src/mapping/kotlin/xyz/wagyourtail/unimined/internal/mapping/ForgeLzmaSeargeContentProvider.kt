package xyz.wagyourtail.unimined.internal.mapping

import okio.BufferedSource
import okio.Buffer
import okio.source
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.Configuration
import xyz.wagyourtail.unimined.mapping.resolver.ContentProvider
import xyz.wagyourtail.unimined.util.getFiles
import xyz.wagyourtail.unimined.util.readZipInputStreamFor
import kotlin.io.path.toPath

internal class ForgeLzmaSeargeContentProvider(
    private val mappings: Configuration,
    private val dep: Dependency,
    private val ext: String,
    private val lzmaFileName: String,
) : ContentProvider {

    override suspend fun resolve() {
        mappings.resolve()
    }

    override fun fileName(): String {
        return lzmaFileName.removeSuffix(".lzma") + ".srg"
    }

    override fun content(): BufferedSource {
        val jarFile = mappings.getFiles(dep) { it.extension == ext }.singleFile.toPath()
        val buffer = Buffer()
        jarFile.readZipInputStreamFor(lzmaFileName) { lzmaStream ->
            LZMACompressorInputStream(lzmaStream).use { decompressed ->
                buffer.writeAll(decompressed.source())
            }
        }
        return buffer
    }
}
