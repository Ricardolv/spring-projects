package com.richard.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.richard.brewer.thymeleaf.processor.ClassForErrrorAttributeTagProcessor;
import com.richard.brewer.thymeleaf.processor.MenuAttrbuteTagProcessor;
import com.richard.brewer.thymeleaf.processor.MessageElementTagProcessor;
import com.richard.brewer.thymeleaf.processor.OrderElementTagProcessor;
import com.richard.brewer.thymeleaf.processor.PaginationElementTagProcessor;

@Component
public class BrewerDialect extends AbstractProcessorDialect {
	
	private static final String NAME = "Project Brewer";
	private static final String PREFIX = "brewer";

	public BrewerDialect() {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new ClassForErrrorAttributeTagProcessor(dialectPrefix));
		processors.add(new MessageElementTagProcessor(dialectPrefix));
		processors.add(new OrderElementTagProcessor(dialectPrefix));
		processors.add(new PaginationElementTagProcessor(dialectPrefix));
		processors.add(new MenuAttrbuteTagProcessor(dialectPrefix));
		return processors;
	}

}
