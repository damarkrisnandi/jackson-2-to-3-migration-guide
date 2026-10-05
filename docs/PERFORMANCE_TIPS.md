# Performance Tips

1. **Reuse one mapper.** Mappers are immutable and thread-safe; create once (singleton bean/static final).
2. **Reuse readers/writers.** `mapper.readerFor(Foo.class)` / `writerFor(...)` are cheap to cache.
3. **Avoid the tree model for large payloads.** Bind directly to POJOs/records, or stream with `JsonParser`/`JsonGenerator`.
4. **Stream huge arrays.** Use `mapper.readerFor(Foo.class).readValues(...)`.
5. **Remove unneeded modules.** java.time support is built in; drop the old extra modules and registration code.
6. **Prefer `byte[]`/`InputStream` input** over `String` to skip a decoding step.
7. **Benchmark yourself** (JMH) before and after migrating; do not assume speedups.
