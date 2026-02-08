package com.malex;

import java.time.LocalDateTime;

public record Event(String uuid, String text, LocalDateTime timestamp) {}
