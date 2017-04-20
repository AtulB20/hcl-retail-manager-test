package com.retail.manager;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.ContentModifyingOperationPreprocessor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target\\generated-snippets")
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	
	protected RestDocumentationResultHandler restDoc(String name){
		ContentModifyingOperationPreprocessor jsonSource = new ContentModifyingOperationPreprocessor(new JsonSyntaxHighlighter());
		return MockMvcRestDocumentation.document(name,
				preprocessRequest(removeHeaders("Host", "Content-Length"), prettyPrint()),
				preprocessResponse(removeHeaders("X-Application-Context", "Context-length"), prettyPrint(), jsonSource));
	}
}
