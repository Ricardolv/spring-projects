package com.richard.batch.infrastructure.batch.demonstrative.reader;

import com.richard.batch.domain.GroupRelease;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Component
public class GroupReleaseReader implements ItemStreamReader<GroupRelease>,
        ResourceAwareItemReaderItemStream<GroupRelease> {
    private final JdbcCursorItemReader<GroupRelease> delegate;
    private GroupRelease currentGroupRelease;

    @Override
    public void setResource(Resource resource) {}

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public GroupRelease read() throws Exception {

        if (isNull(currentGroupRelease))
            currentGroupRelease = delegate.read();

        GroupRelease groupRelease = currentGroupRelease;
        currentGroupRelease = null;

        if (nonNull(groupRelease)) {
            GroupRelease nextGroupRelease = peek();
            while (isReleaseRelaships(groupRelease, nextGroupRelease)) {
                    groupRelease.getReleases().add(currentGroupRelease.getReleaseTmp());
                    nextGroupRelease = peek();
            }
            groupRelease.getReleases().add(groupRelease.getReleaseTmp());
        }

        return groupRelease;
    }

    private GroupRelease peek() throws Exception {
        currentGroupRelease = delegate.read();
        return currentGroupRelease;
    }

    private boolean isReleaseRelaships(GroupRelease groupRelease, GroupRelease nextGroupRelease) {
        return nonNull(nextGroupRelease)
               && groupRelease.getCodeNatureExpense().equals(nextGroupRelease.getCodeNatureExpense());
    }

}
