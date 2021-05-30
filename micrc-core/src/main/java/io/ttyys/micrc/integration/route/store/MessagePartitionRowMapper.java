package io.ttyys.micrc.integration.route.store;

import org.springframework.integration.jdbc.store.channel.MessageRowMapper;
import org.springframework.integration.support.converter.AllowListDeserializingConverter;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.messaging.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessagePartitionRowMapper extends MessageRowMapper {

    public MessagePartitionRowMapper() {
        super(new AllowListDeserializingConverter(), new DefaultLobHandler());
    }

    @Override
    public Message<?> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message<?> message = super.mapRow(rs, rowNum);
        if (message != null) {
            message.getHeaders().put(PartitionCapableChannelMessageStore.MESSAGE_HEADER_PARTITION_SEQUENCE,
                    rs.getLong("MESSAGE_SEQUENCE"));
            message.getHeaders().put(PartitionCapableChannelMessageStore.MESSAGE_HEADER_PARTITION_CREATED_AT,
                    rs.getLong("CREATED_DATE"));
        }
        return message;
    }
}
