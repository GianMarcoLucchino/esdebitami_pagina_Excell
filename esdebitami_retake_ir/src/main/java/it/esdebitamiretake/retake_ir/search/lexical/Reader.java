package it.esdebitamiretake.retake_ir.search.lexical;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.AfterEffectL;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BasicModelIn;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.DFRSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.search.similarities.NormalizationH2;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;

import it.esdebitamiretake.retake_ir.search.Language;


public class Reader extends Index {

	private final IndexReader reader;
	private final IndexSearcher searcher;
	private final QueryParser parser;

	public enum ALGORITHM {

		TFIDF, BM25, DFR, LM
	}

	public Reader(Language language,String dir) throws IOException {

		this(language,dir, ALGORITHM.BM25);
	}

	public Reader(Language language,String dir, ALGORITHM algo) throws IOException {

		super(language);
		
		this.reader = createReader(dir);
		this.searcher = new IndexSearcher(reader);
		this.searcher.setSimilarity(buildSearcher(algo));
		this.parser = new QueryParser(Field.TEXT.name(), getAnalyzer());
		this.parser.setSplitOnWhitespace(true);
		this.parser.setAutoGeneratePhraseQueries(true);
		this.parser.setAutoGenerateMultiTermSynonymsPhraseQuery(true);
		
	}

	private IndexReader createReader(String dir) throws IOException {

		return DirectoryReader.open(FSDirectory.open(Paths.get(dir)));
	}

	private Similarity buildSearcher(ALGORITHM algo) {

		Similarity similarity;

		switch (algo) {

		case BM25:
			similarity = new BM25Similarity();
			break;

		case DFR:
			similarity = new DFRSimilarity(new BasicModelIn(), new AfterEffectL(), new NormalizationH2());
			break;

		case LM:
			similarity = new LMDirichletSimilarity();
			break;

		default:
			similarity = new ClassicSimilarity();
		}

		return similarity;
	}

	Map<String, Float> query(String text, float threshold, String id) throws IOException, ParseException {

		Query query = parser.parse(text);
		Map<String, Float> documents = new HashMap<>();
		ScoreDoc[] scoreDocs = searcher.search(query, 100).scoreDocs;

		for (ScoreDoc scoreDoc : scoreDocs) {
			float score = scoreDoc.score / scoreDocs[0].score;

			if (score >= threshold)
				documents.put(searcher.doc(scoreDoc.doc).get(id), score);
			else
				break;
		}

		return documents;

	}

	@Override
	public void close() throws IOException {

		reader.close();
	}

}
