package com.ta.coremolde.master.service;

import java.io.ByteArrayInputStream;

public interface ExportService {

    ByteArrayInputStream exportProduct();

    ByteArrayInputStream exportOrder();

}
