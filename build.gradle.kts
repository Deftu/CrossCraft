plugins {
    id("xyz.unifycraft.gradle.multiversion-root")
}

preprocess {
    val fabric11902 = createNode("1.19.2-fabric", 11902, "yarn")
    val fabric11802 = createNode("1.18.2-fabric", 11802, "yarn")
    val fabric11701 = createNode("1.17.1-fabric", 11701, "yarn")
    val fabric11605 = createNode("1.16.5-fabric", 11605, "yarn")

    fabric11902.link(fabric11802)
    fabric11802.link(fabric11701)
    fabric11701.link(fabric11605)
}
