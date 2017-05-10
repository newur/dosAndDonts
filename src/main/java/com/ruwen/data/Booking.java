package com.ruwen.data;

import com.ruwen.data.enums.ModeOfTransport;
import lombok.Builder;
import lombok.Data;

/**
 * Created by ruwen on 24.03.17.
 */
@Data
@Builder
public class Booking {

    private String trackingNumber;
    private ModeOfTransport modeOfTransport;
}
