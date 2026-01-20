using Microsoft.AspNetCore.Mvc;

namespace payment_service_dotnet.Controllers
{
    [ApiController]
    [Route("health")]
    public class HealthController : ControllerBase
    {
        [HttpGet]
        public string Get() => "Payment Service Running from dot net";
    }
}

