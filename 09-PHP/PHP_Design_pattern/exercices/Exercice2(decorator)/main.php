<?php

// Interface Observer
interface Observer {
    public function update(string $event): void;
}

// Sujet (Event Manager)
class EventManager {
    private array $observers = [];

    public function subscribe(Observer $observer): void {
        $this->observers[] = $observer;
    }

    public function unsubscribe(Observer $observer): void {
        $this->observers = array_filter(
            $this->observers,
            fn($obs) => $obs !== $observer
        );
    }

    public function notify(string $event): void {
        foreach ($this->observers as $observer) {
            $observer->update($event);
        }
    }
}

// Observateurs concrets
class EmailNotifier implements Observer {
    public function update(string $event): void {
        echo "📧 Email envoyé pour l'événement : $event\n";
    }
}

class LogNotifier implements Observer {
    public function update(string $event): void {
        echo "🗒 Événement loggé : $event\n";
    }
}

// ----- Test principal -----
$manager = new EventManager();

$email = new EmailNotifier();
$log = new LogNotifier();

$manager->subscribe($email);
$manager->subscribe($log);

$manager->notify("Nouvelle inscription");
$manager->notify("Erreur système");

?>
