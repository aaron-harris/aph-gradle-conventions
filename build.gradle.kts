tasks {
    create("run", Exec::class.java) {
        commandLine("echo", "Hello, World!")
    }
}
