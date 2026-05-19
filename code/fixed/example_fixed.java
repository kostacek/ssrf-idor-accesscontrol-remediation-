@GetMapping("/fetch")
@ResponseBody
public String fetchRemote(@RequestParam String url) {
    // Vulnerable: fetches whatever URL the user provides
    return restTemplate.getForObject(url, String.class);
}
@GetMapping("/fetch")
@ResponseBody
public String fetchRemote(@RequestParam String url) {
    try {
        URI uri = new URI(url);

        // Accept only http/https schemes
        if (!"http".equalsIgnoreCase(uri.getScheme()) &&
            !"https".equalsIgnoreCase(uri.getScheme())) {
            throw new IllegalArgumentException("Unsupported scheme");
        }

        // Allowlist of permitted hosts
        Set<String> allowedHosts = Set.of("api.internal.example.com", "images.example.com");
        String host = uri.getHost();
        if (host == null || !allowedHosts.contains(host.toLowerCase())) {
            throw new IllegalArgumentException("Untrusted host");
        }

        // Optionally validate the path/port
        return restTemplate.getForObject(uri, String.class);
    } catch (Exception e) {
        // Log and return error
        return "Invalid or unauthorized URL";
    }
}
