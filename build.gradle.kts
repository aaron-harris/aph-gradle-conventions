tasks {
    create("check", Exec::class.java) {
        commandLine("echo", "Hello, World!")
    }
}
